package com.meysamasadian.channelmanagement.service;

import com.meysamasadian.channelmanagement.dao.AccountDao;
import com.meysamasadian.channelmanagement.dao.TransactionDao;
import com.meysamasadian.channelmanagement.dto.AccountDto;
import com.meysamasadian.channelmanagement.dto.TransactionDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentContainer;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;
import com.meysamasadian.channelmanagement.exception.BusinessException;
import com.meysamasadian.channelmanagement.model.Account;
import com.meysamasadian.channelmanagement.model.Transaction;
import com.meysamasadian.channelmanagement.service.treasury.TreasuryServiceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.ProcessingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rahnema on 9/6/2017.
 */
@Service
@PropertySource(value = {"classpath:application.properties"})
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TreasuryServiceConnector connector;

    @Autowired
    private Environment environment;

    public void register(AccountDto accountDto) throws BusinessException {
        try {
            accountDao.save(convert(accountDto));
            connector.register(getTreasuryAccount(accountDto, accountDto.getInitAmount()));
        } catch (BusinessException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    private TreasuryAccountDto getTreasuryAccount(AccountDto accountDto, BigDecimal initBalance) {
        TreasuryAccountDto dto = new TreasuryAccountDto();
        dto.setPan(accountDto.getPhone());
        dto.setBalance(initBalance);
        return dto;
    }

    private TreasuryAccountDto getTreasuryAccount(AccountDto accountDto) {
        TreasuryAccountDto dto = new TreasuryAccountDto();
        dto.setPan(accountDto.getPhone());
        return dto;
    }

    public String login(String phoneNumber) throws BusinessException {
        return connector.login(phoneNumber);
    }

    public void transfer(TransactionDto transactionDto, String otp) throws BusinessException {
        TreasuryDocumentContainer partOne;
        try {
           partOne = connector.issueDocument(generatePartOneDocument(transactionDto), otp);
        } catch (ProcessingException pe) {
            pe.printStackTrace();
            throw new BusinessException(BusinessException.TIMEOUT_FROM_SOURCE_ACCOUNT);
        }
        if (!partOne.isOk()) {
            throw new BusinessException(partOne.getMessage());
        }

        String channelOtp = login(environment.getRequiredProperty("channel_management.pan"));
        TreasuryDocumentContainer partTwo;
        try {
           partTwo = connector.issueDocument(generatePartTwoDocument(transactionDto), channelOtp);
        } catch (ProcessingException pe) {
            pe.printStackTrace();
            reverse(partOne.getRefId());
            throw new BusinessException(BusinessException.TIMEOUT_FROM_TARGET_ACCOUNT);
        }

        if (!partTwo.isOk()) {
            reverse(partOne.getRefId());
            throw new BusinessException(partTwo.getMessage());
        }
        Transaction transaction = convert(transactionDto);
        transaction.setPartOneRefId(partOne.getRefId());
        transaction.setPartTwoRefId(partTwo.getRefId());
        transaction.setRefId(generateRefId());
        transactionDao.save(transaction);
    }

    private String generateRefId() {
        return "TX_"+System.currentTimeMillis();
    }

    private TreasuryDocumentDto generatePartTwoDocument(TransactionDto transactionDto) {
        TreasuryDocumentDto treasuryDocumentDto = new TreasuryDocumentDto();
        treasuryDocumentDto.setAmount(transactionDto.getAmount());
        treasuryDocumentDto.setSource(environment.getRequiredProperty("channel_management.pan"));
        treasuryDocumentDto.setDest(transactionDto.getDest());
        return treasuryDocumentDto;    }

    private TreasuryDocumentDto generatePartOneDocument(TransactionDto transactionDto) {
        TreasuryDocumentDto treasuryDocumentDto = new TreasuryDocumentDto();
        treasuryDocumentDto.setAmount(transactionDto.getAmount());
        treasuryDocumentDto.setSource(transactionDto.getSource());
        treasuryDocumentDto.setDest(environment.getRequiredProperty("channel_management.pan"));
        return treasuryDocumentDto;
    }

    private TreasuryDocumentDto getTreasuryDocumentDto(TransactionDto transactionDto) {
        TreasuryDocumentDto dto = new TreasuryDocumentDto();
        dto.setAmount(transactionDto.getAmount());
        dto.setSource(transactionDto.getSource());
        dto.setDest(transactionDto.getSource());
        return dto;
    }

    public void reverse(String refId) {
        connector.reverseDocument(refId);
    }

    public List<TransactionDto> list(String phone) {
        return transactionDao.listAll(phone).stream()
                .map(transaction -> present(transaction)).collect(Collectors.toList());
    }

    public AccountDto present(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setFullName(account.getFullName());
        dto.setPhone(account.getPhone());
        return dto;
    }

    public TransactionDto present(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        dto.setDest(transaction.getDest().getPhone());
        dto.setSource(transaction.getSource().getPhone());
        return dto;
    }

    public Account convert(AccountDto dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setFullName(dto.getFullName());
        account.setPhone(dto.getPhone());
        return account;
    }

    public Transaction convert(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setDate(new Date().toString());
        transaction.setAmount(dto.getAmount());
        transaction.setSource(accountDao.load(dto.getSource()));
        transaction.setDest(accountDao.load(dto.getDest()));
        return transaction;
    }
}

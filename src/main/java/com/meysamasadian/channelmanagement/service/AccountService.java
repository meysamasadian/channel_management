package com.meysamasadian.channelmanagement.service;

import com.meysamasadian.channelmanagement.dao.AccountDao;
import com.meysamasadian.channelmanagement.dao.TransactionDao;
import com.meysamasadian.channelmanagement.dto.AccountDto;
import com.meysamasadian.channelmanagement.dto.TransactionDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;
import com.meysamasadian.channelmanagement.model.Account;
import com.meysamasadian.channelmanagement.model.Transaction;
import com.meysamasadian.channelmanagement.service.treasury.TreasuryServiceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rahnema on 9/6/2017.
 */
@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TreasuryServiceConnector connector;

    public void register(AccountDto accountDto, BigDecimal initBalance) {
        accountDao.save(convert(accountDto));
        connector.register(getTreasuryAccount(accountDto, initBalance));
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

    public String login(String phoneNumber) {
        return connector.login(phoneNumber);
    }

    public void transfer(TransactionDto transactionDto, String otp) {
        transactionDao.save(convert(transactionDto));
        connector.issueDocument(getTreasuryDocumentDto(transactionDto), otp);
    }

    private TreasuryDocumentDto getTreasuryDocumentDto(TransactionDto transactionDto) {
        TreasuryDocumentDto dto = new TreasuryDocumentDto();
        dto.setAmount(transactionDto.getAmount());
        dto.setSource(getTreasuryAccount(transactionDto.getSource()));
        dto.setDest(getTreasuryAccount(transactionDto.getSource()));
        return dto;
    }

    public void reverse(AccountDto dto, String otp) {
       //todo save reverse transaction
        connector.reverseDocument(getTreasuryAccount(dto), otp);
    }

    public List<TransactionDto> list(AccountDto dto) {
        return transactionDao.listAll(dto.getPhone()).stream()
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
        dto.setDest(present(transaction.getDest()));
        dto.setSource(present(transaction.getSource()));
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
        transaction.setSource(convert(dto.getSource()));
        transaction.setDest(convert(dto.getDest()));
        return transaction;
    }
}

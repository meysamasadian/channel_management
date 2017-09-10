package com.meysamasadian.channelmanagement.service.treasury;

import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentContainer;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;
import com.meysamasadian.channelmanagement.exception.BusinessException;
import org.springframework.stereotype.Service;

/**
 * Created by rahnema on 9/6/2017.
 */
@Service
public class TreasuryServiceConnectorImpl implements TreasuryServiceConnector {
    @Override
    public String register(TreasuryAccountDto dto) throws BusinessException {
        return (String) ConnectionUtils.request(Path.REGISTER_ACCOUNT, String.class, null, dto);
    }

    @Override
    public String login(String phoneNumber) throws BusinessException {
        return (String) ConnectionUtils.request(Path.LOGIN, String.class, phoneNumber, null);
    }

    @Override
    public TreasuryDocumentContainer issueDocument(TreasuryDocumentDto dto, String otp)  {
        return (TreasuryDocumentContainer) ConnectionUtils.transact(Path.ISSUE_DOCUMENT, TreasuryDocumentContainer.class, otp, dto);
    }

    @Override
    public TreasuryDocumentContainer reverseDocument(String refId) {
        return (TreasuryDocumentContainer) ConnectionUtils.transact(Path.REVERSE_DOCUMENT, TreasuryDocumentContainer.class, refId, null);
    }

}

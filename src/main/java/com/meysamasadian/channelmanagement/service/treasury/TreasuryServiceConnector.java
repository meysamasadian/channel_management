package com.meysamasadian.channelmanagement.service.treasury;

import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentContainer;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;
import com.meysamasadian.channelmanagement.exception.BusinessException;

/**
 * Created by rahnema on 9/6/2017.
 */
public interface TreasuryServiceConnector {
    String register(TreasuryAccountDto dto) throws BusinessException;
    String login(String phoneNumber) throws BusinessException;
    TreasuryDocumentContainer issueDocument(TreasuryDocumentDto dto, String otp);
    TreasuryDocumentContainer reverseDocument(String refId);
}

package com.meysamasadian.channelmanagement.service.treasury;

import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;

/**
 * Created by rahnema on 9/6/2017.
 */
public interface TreasuryServiceConnector {
    String register(TreasuryAccountDto dto);
    String login(String phoneNumber);
    String issueDocument(TreasuryDocumentDto dto, String otp);
    String reverseDocument(TreasuryAccountDto dto, String otp);
}

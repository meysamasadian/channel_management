package com.meysamasadian.channelmanagement.service.treasury;

import com.meysamasadian.channelmanagement.dto.treasury.TreasuryAccountDto;
import com.meysamasadian.channelmanagement.dto.treasury.TreasuryDocumentDto;
import org.springframework.stereotype.Service;

/**
 * Created by rahnema on 9/6/2017.
 */
@Service
public class TreasuryServiceConnectorImpl implements TreasuryServiceConnector {
    @Override
    public String register(TreasuryAccountDto dto) {
        return (String) ConnectionUtils.request(Path.REGISTER_ACCOUNT, null, dto);
    }

    @Override
    public String login(String phoneNumber) {
        return (String) ConnectionUtils.request(Path.LOGIN, phoneNumber, null);
    }

    @Override
    public String issueDocument(TreasuryDocumentDto dto, String otp) {
        return (String) ConnectionUtils.request(Path.ISSUE_DOCUMENT, otp, dto);
    }

    @Override
    public String reverseDocument(TreasuryAccountDto dto, String otp) {
        return (String) ConnectionUtils.request(Path.REVERSE_DOCUMENT, otp, dto);
    }

}

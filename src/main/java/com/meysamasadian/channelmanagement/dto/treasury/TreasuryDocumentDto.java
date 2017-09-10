package com.meysamasadian.channelmanagement.dto.treasury;

import java.math.BigDecimal;

/**
 * Created by rahnema on 9/6/2017.
 */
public class TreasuryDocumentDto {
    private long id;
    private BigDecimal amount;
    private TreasuryAccountDto source;
    private TreasuryAccountDto dest;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TreasuryAccountDto getSource() {
        return source;
    }

    public void setSource(TreasuryAccountDto source) {
        this.source = source;
    }

    public TreasuryAccountDto getDest() {
        return dest;
    }

    public void setDest(TreasuryAccountDto dest) {
        this.dest = dest;
    }
}

package com.meysamasadian.channelmanagement.dto;

import com.meysamasadian.channelmanagement.model.Account;

import java.math.BigDecimal;

/**
 * Created by rahnema on 9/6/2017.
 */
public class TransactionDto {
    private long id;
    private BigDecimal amount;
    private String date;
    private String source;
    private String dest;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}

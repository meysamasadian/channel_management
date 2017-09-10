package com.meysamasadian.channelmanagement.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rahnema on 9/6/2017.
 */
public class AccountDto implements Serializable {
    private long id;
    private String fullName;
    private String phone;
    private BigDecimal initAmount;

    public BigDecimal getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(BigDecimal initAmount) {
        this.initAmount = initAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

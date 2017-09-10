package com.meysamasadian.channelmanagement.dto;

/**
 * Created by rahnema on 9/6/2017.
 */
public class AccountDto {
    private long id;
    private String fullName;
    private String phone;

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

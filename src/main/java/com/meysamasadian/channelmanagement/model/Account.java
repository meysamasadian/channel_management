package com.meysamasadian.channelmanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rahnema on 9/6/2017.
 */
@Entity
@Table(name = "cm_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "c_fullname")
    private String fullName;

    @Column(name = "c_phone")
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

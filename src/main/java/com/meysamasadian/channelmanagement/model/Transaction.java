package com.meysamasadian.channelmanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rahnema on 9/6/2017.
 */
@Entity
@Table(name = "cm_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "c_amount")
    private BigDecimal amount;

    @Column(name = "c_date")
    private String date;

    @Column(name = "c_source")
    @OneToOne
    @JoinColumn(name="c_source")
    private Account source;

    @Column(name = "c_dest")
    @OneToOne
    @JoinColumn(name="c_dest")
    private Account dest;

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

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDest() {
        return dest;
    }

    public void setDest(Account dest) {
        this.dest = dest;
    }
}

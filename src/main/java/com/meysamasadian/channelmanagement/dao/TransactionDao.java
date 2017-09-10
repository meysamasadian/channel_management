package com.meysamasadian.channelmanagement.dao;


import com.meysamasadian.channelmanagement.model.Transaction;

import java.util.List;

/**
 * Created by rahnema on 9/6/2017.
 */
public interface TransactionDao extends GenericDao<Transaction> {
    List<Transaction> listAll(String phone);
}

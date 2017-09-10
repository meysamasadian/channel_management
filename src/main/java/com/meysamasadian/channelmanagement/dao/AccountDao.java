package com.meysamasadian.channelmanagement.dao;


import com.meysamasadian.channelmanagement.model.Account;

/**
 * Created by rahnema on 9/6/2017.
 */
public interface AccountDao extends GenericDao<Account> {
    Account load(String pan);
}

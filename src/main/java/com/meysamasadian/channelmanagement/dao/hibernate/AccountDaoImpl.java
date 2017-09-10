package com.meysamasadian.channelmanagement.dao.hibernate;

import com.meysamasadian.channelmanagement.dao.AccountDao;
import com.meysamasadian.channelmanagement.model.Account;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by rahnema on 9/6/2017.
 */

@Repository("accountDao")
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {
    protected AccountDaoImpl() {
        super(Account.class);
    }

    @Override
    public Account load(String pan) {
        StringBuilder builder = new StringBuilder(" from Account acc where acc.phone = :pan");
        Query query =  sessionFactory.getCurrentSession().createQuery(builder.toString()).setString("pan",pan);
        return (Account) query.uniqueResult();
    }

    @Override
    public void save(Account account) {
        super.save(account);
    }

    @Override
    public void update(Account account) {
        super.update(account);
    }

    @Override
    public void delete(Account account) {
        super.delete(account);
    }

    @Override
    public Account load(long id) {
        return super.load(id);
    }
}

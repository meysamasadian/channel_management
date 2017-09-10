package com.meysamasadian.channelmanagement.dao.hibernate;

import com.meysamasadian.channelmanagement.dao.TransactionDao;
import com.meysamasadian.channelmanagement.model.Transaction;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rahnema on 9/6/2017.
 */

@Repository("transactionDao")
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao {
    protected TransactionDaoImpl() {
        super(Transaction.class);
    }

    @Override
    public void save(Transaction transaction) {
        super.save(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        super.update(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        super.delete(transaction);
    }

    @Override
    public Transaction load(long id) {
        return super.load(id);
    }

    @Override
    public List<Transaction> listAll(String phone) {
        //noinspection JpaQlInspection
        Query query = sessionFactory.getCurrentSession()
                .createQuery(" from Transaction trx where trx.source.phone = :phone or trx.dest.phone = :phone")
                .setString("phone",phone);
        return query.list();
    }
}

package com.meysamasadian.channelmanagement.dao;

/**
 * Created by rahnema on 9/6/2017.
 */
public interface GenericDao<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
    T load(long id);
}

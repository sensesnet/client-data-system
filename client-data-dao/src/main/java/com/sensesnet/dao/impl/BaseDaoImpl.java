package com.sensesnet.dao.impl;

import com.sensesnet.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * Base Dao implements crud methods
 * <p>
 * Propagation.MANDATORY — обратный по отношению к Propagation.REQUIRES_NEW:
 * всегда используется существующая транзакция и кидается исключение, если текущей транзакции нет.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
abstract class BaseDaoImpl<T> implements BaseDao<T>
{
    @Autowired
    SessionFactory sessionFactory;

    public T add(T t)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        return t;
    }

    public void update(T t)
    {
        sessionFactory.getCurrentSession().update(t);
    }

    public void delete(T t)
    {
        sessionFactory.getCurrentSession().delete(t);
    }

    public T getBeanById(Class<T> clazz, Integer id)
    {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }
}

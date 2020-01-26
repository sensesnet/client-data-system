package com.sensesnet.dao.impl;

import com.sensesnet.dao.UserDao;
import com.sensesnet.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User dao implementation
 *
 *  * Propagation.MANDATORY — обратный по отношению к Propagation.REQUIRES_NEW:
 *  * всегда используется существующая транзакция и кидается исключение, если текущей транзакции нет.
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>
{
    /**
     *  - get list of user
     *
     * @return
     */
    @Override
    public List<User> getAll()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM User u").list();
    }

    /**
     * - get user by login
     *
     * @param userLogin
     * @return
     */
    @Override
    public User getUserByLogin(final String userLogin)
    {
        return (User) sessionFactory.getCurrentSession().
                createQuery("FROM User u WHERE u.userLogin = :userLogin").
                setParameter("userLogin", userLogin).uniqueResult();
    }

    /**
     * - get user by Id
     *
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public User getBeanById(Class<User> clazz, Integer id)
    {
        return (User) sessionFactory.getCurrentSession().
                createQuery("FROM User u WHERE u.userId = :id").
                setParameter("id", id).uniqueResult();
    }
}

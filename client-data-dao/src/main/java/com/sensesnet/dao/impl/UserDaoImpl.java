package com.sensesnet.dao.impl;

import com.sensesnet.dao.UserDao;
import com.sensesnet.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User dao implementation
 * <p>
 * * Propagation.MANDATORY — обратный по отношению к Propagation.REQUIRES_NEW:
 * * всегда используется существующая транзакция и кидается исключение, если текущей транзакции нет.
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>
{
    /**
     * - get list of user
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

    @Value("${app.limit.users}")
    private int maxUsersOnPage;

    @Override
    public List<User> getByFirst(int startPosition)
    {
        int usersOnPage = maxUsersOnPage;
        List<User> userList = getAll();

        if (startPosition <= userList.size())
        {
            if (maxUsersOnPage >= userList.size() - startPosition)
            {
                usersOnPage = userList.size() - startPosition * maxUsersOnPage;
            }
        }
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setFirstResult(startPosition)
                .setMaxResults(usersOnPage).list();
    }

    @Override
    public User getUserByLoginAndPassword(String userLogin, String encryptPassword)
    {

        return (User) sessionFactory.getCurrentSession().
                createQuery("FROM User u WHERE u.userLogin = :userLogin AND u.userPassword = :userPassword")
                .setParameter("userLogin", userLogin)
                .setParameter("userPassword", encryptPassword)
                .uniqueResult();
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

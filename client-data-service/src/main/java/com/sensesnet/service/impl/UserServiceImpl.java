package com.sensesnet.service.impl;

import com.sensesnet.service.UserService;
import com.sensesnet.dao.UserDao;
import com.sensesnet.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User Service
 */

@Service
@Transactional
public class UserServiceImpl implements UserService
{

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getAll()
    {
        List<User> userList = userDao.getAll();
        if (userList != null)
        {
            log.info("user list:{}", userList);
            return userList;
        }
        return null;
    }

    @Override
    public User add(User user)
    {
        if (user != null && user.getUserLogin() != null && !user.getUserLogin().isEmpty()
                && user.getUserPassword() != null && !user.getUserPassword().isEmpty())
        {
            User userdb = getUserByLogin(user.getUserLogin());
            if (userdb == null)
            {
                userDao.add(user);
                log.info("new user registration:{}", user.getUserName());
                return user;
            }
        }
        return null;
    }

    @Override
    public void remove(User user)
    {
        userDao.delete(user);
        log.info("user has been deleted:{}", user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Class<User> clazz, Integer id)
    {
        User user = (User) userDao.getBeanById(clazz, id);
        log.info("user:{}", user);
        return user;
    }

    @Override
    public void edit(User user)
    {
        userDao.update(user);
        log.info("user has been updated:{}", user);
    }

    @Override
    public User getUserByLogin(String userLogin)
    {
        User user = (User) userDao.getUserByLogin(userLogin);
        log.info("user:{}", user);
        return user;
    }

    @Override
    public void removeById(Integer userId)
    {
        User user = (User) userDao.getBeanById(User.class, userId);
        if (user != null)
        {
            userDao.delete(user);
            log.info("user has been deleted:{}", user);
        }
    }

    @Value("${app.limit.users}")
    private int maxUsersOnPage;

    @Override
    public List<User> getByPage(Long page)
    {
        if (page == null || page < 1 ) page = 1L;
        int startPosition = (int) (page - 1) * maxUsersOnPage;
        return userDao.getByFirst(startPosition);
    }

    @Override
    public User getUserByLoginAndPassword(String userLogin, String encryptPassword)
    {
        User user = (User) userDao.getUserByLoginAndPassword(userLogin, encryptPassword);
        return user;
    }
}

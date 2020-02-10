package com.sensesnet.dao;

import com.sensesnet.model.User;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User DAO
 */
public interface UserDao<T> extends BaseDao<T>
{
    /**
     * Return list of user
     *
     * @return
     */
    List<T> getAll();


    /**
     * Return user by login
     *
     * @param userLogin
     * @return
     */
    T getUserByLogin(String userLogin);


    List<T> getByFirst(int startPosition);

    /**
     * Auth process
     *
     * @param userLogin
     * @param encryptPassword
     * @return
     */
    T getUserByLoginAndPassword(String userLogin, String encryptPassword);
}

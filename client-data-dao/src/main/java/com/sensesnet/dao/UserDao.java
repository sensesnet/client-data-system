package com.sensesnet.dao;

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
}

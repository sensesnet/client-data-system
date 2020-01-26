package com.sensesnet.service;

import com.sensesnet.model.User;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User service interface CRUD
 */
public interface UserService
{
    List<User> getAll();

    User add(User user);

    void remove(User user);

    User getById(Class<User> clazz, Integer id);

    void edit(User user);

    User getUserByLogin(String userLogin);
}

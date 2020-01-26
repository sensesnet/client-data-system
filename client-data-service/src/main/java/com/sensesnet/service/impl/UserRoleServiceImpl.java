package com.sensesnet.service.impl;

import com.sensesnet.service.UserRoleService;
import com.sensesnet.model.User;
import com.sensesnet.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User Role Service
 */

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService
{

    private final static Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Override
    public UserRole add(UserRole user)
    {
        return null;
    }

    @Override
    public User getRoleByName(String userName)
    {
        return null;
    }
}

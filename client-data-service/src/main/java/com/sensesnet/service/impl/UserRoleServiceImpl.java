package com.sensesnet.service.impl;

import com.sensesnet.dao.UserDao;
import com.sensesnet.dao.UserRoleDao;
import com.sensesnet.service.UserRoleService;
import com.sensesnet.model.User;
import com.sensesnet.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRole add (UserRole userRole)
    {
        if (userRole != null && userRole.getRoleName() != null)
        {
            UserRole userRoledb = getRoleByName(userRole.getRoleName());
            if (userRoledb == null)
            {
                userRoleDao.add(userRole);
                log.info("new user role has been added:{}", userRole.getRoleName());
                return userRole;
            }
        }
        return null;
    }

    @Override
    public UserRole getRoleByName (String roleName)
    {
        UserRole userRole = (UserRole) userRoleDao.getRoleByName(roleName);
        log.info("role :{}", userRole);
        return userRole;
    }

    @Override
    public UserRole getRoleById (Integer roleId)
    {
        UserRole userRole = (UserRole) userRoleDao.getBeanById(UserRole.class, roleId);
        log.info("role :{}", userRole);
        return userRole;
    }
}

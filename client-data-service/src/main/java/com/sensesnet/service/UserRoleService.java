package com.sensesnet.service;

import com.sensesnet.model.User;
import com.sensesnet.model.UserRole;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User role service interface
 */
public interface UserRoleService
{
    UserRole add(UserRole user);

    UserRole getRoleByName(String userRoleName);

    UserRole getRoleById (Integer userRole);
}

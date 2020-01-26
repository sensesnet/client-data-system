package com.sensesnet.dao;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User Role DAO
 */
public interface UserRoleDao<T> extends BaseDao<T>
{
    /**
     * Return list of roles
     *
     * @return
     */
    List<T> getAll();
}

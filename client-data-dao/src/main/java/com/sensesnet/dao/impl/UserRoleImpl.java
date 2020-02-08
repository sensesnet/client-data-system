package com.sensesnet.dao.impl;

import com.sensesnet.dao.UserRoleDao;
import com.sensesnet.model.User;
import com.sensesnet.model.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User role implementation
 *
 * Propagation.MANDATORY — обратный по отношению к Propagation.REQUIRES_NEW:
 * всегда используется существующая транзакция и кидается исключение, если текущей транзакции нет.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserRoleImpl extends BaseDaoImpl<UserRole> implements UserRoleDao<UserRole>
{
    /**
     * - get list of roles
     *
     * @return
     */
    @Override
    public List<UserRole> getAll()
    {
        return sessionFactory.getCurrentSession().
                createQuery("FROM UserRole u").list();
    }

    @Override
    public UserRole getRoleByName (String roleName)
    {
        return (UserRole) sessionFactory.getCurrentSession().
                createQuery("FROM UserRole u WHERE u.roleName = :roleName").
                setParameter("roleName",roleName).uniqueResult();
    }

    /**
     * get UserRole by id
     *
     * @param clazz
     * @param roleId
     * @return
     */
    @Override
    public UserRole getBeanById(Class<UserRole> clazz, Integer roleId)
    {
        return (UserRole) sessionFactory.getCurrentSession().
                createQuery("FROM UserRole u WHERE u.roleId = :roleId").
                setParameter("roleId", roleId).uniqueResult();
    }
}

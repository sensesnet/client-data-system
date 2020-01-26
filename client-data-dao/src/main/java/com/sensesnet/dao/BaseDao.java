package com.sensesnet.dao;


/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * Base CRUD operations
 */
public interface BaseDao<T> {

    T add(T t);

    void update(T t);

    void delete(T t);

    T getBeanById(Class<T> clazz, Integer id);
}

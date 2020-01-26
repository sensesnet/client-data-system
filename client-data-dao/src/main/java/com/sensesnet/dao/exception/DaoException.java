package com.sensesnet.dao.exception;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * DaoException
 */
public class DaoException extends Exception
{

    public DaoException()
    {
    }

    public DaoException(final String message)
    {
        super(message);
    }

    public DaoException(final String message,
                        final Throwable throwable)
    {
        super(message, throwable);
    }

    public DaoException(final Throwable throwable)
    {
        super(throwable);
    }

    public DaoException(final String message,
                        final Throwable throwable,
                        final boolean enableSuppression,
                        final boolean writableStackTrace)
    {
        super(message, throwable, enableSuppression, writableStackTrace);
    }
}

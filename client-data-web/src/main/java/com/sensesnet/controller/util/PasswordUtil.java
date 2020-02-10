package com.sensesnet.controller.util;

//import org.springframework.util.DigestUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * encript pass
 */
public class PasswordUtil
{
    public static String getEncryptPassword(String password)
    {
        String SALT = "SALT";
//        return password;
        return DigestUtils.sha256Hex(password);// + DigestUtils.sha256Hex(SALT);
    }
}

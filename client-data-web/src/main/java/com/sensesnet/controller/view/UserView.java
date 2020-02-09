package com.sensesnet.controller.view;

import lombok.Data;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * View object fot registration and add new user process
 */
@Data
public class UserView
{
    private String userLogin;

    private String userPassword;

    private String confirmPassword;

    private String userRole;

    private String userName;

    private String userSurname;

    private String userAddress;

    private String userBirthday;

    private String userPhone;
}

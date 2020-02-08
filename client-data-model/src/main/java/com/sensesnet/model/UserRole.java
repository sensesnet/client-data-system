package com.sensesnet.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User role model
 */
@Data
@ToString
@Entity
@Table(name = "user_role")
public class UserRole
{
    @Id
    @Column(name = "roleId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "roleName")
    private String roleName;

    @Column(name="roleDescription")
    private String roleDesc;
}

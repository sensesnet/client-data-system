package com.sensesnet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * User model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userLogin")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User
{
    @Id
    @Column(name = "userId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "userLogin")
    private String userLogin;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "roleId")
    private Integer userRole;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userSurname")
    private String userSurname;

    @Column(name = "userAddress")
    private String userAddress;

    @Column(name = "userBirthday")
    private String userBirthday;

    @Column(name = "userPhone")
    private String userPhone;
}

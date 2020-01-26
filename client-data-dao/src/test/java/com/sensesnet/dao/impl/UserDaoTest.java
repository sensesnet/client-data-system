package com.sensesnet.dao.impl;

import com.sensesnet.dao.UserDao;
import com.sensesnet.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = {"classpath:beans-dao-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest
{

    private final static Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao<User> userDao;

    private String userLogin;

    private User user;

    @Before
    public void setUp()
    {
        User user = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setUserLogin(String.format("test_%s@test.org", timestamp.getTime()));
        user.setUserPassword("Password1");
        user.setUserName("Test");
        user.setUserSurname("Test");
        user.setUserAddress("Armando Bennett 2554 Pede");
        user.setUserBirthday("2019-06-27");
        user.setUserRole(1);
        user.setUserPhone("8 033 661 77 71");
        userDao.add(user);
        this.userLogin = user.getUserLogin();
    }

    @Test
    public void getUserByLogin()
    {
        user = userDao.getUserByLogin(userLogin);
        log.info("user:{}", user);
        assertNotNull("User:[" + user.getUserLogin() + "] has not been found!", user);
    }

    @Test
    public void getAll()
    {
        List<User> userList = userDao.getAll();
        log.info("user list:{}", userList.toString());
        System.out.println(userList.toString());
        assertFalse("List of users is empty:[" + userList + "]", userList.isEmpty());
    }
}

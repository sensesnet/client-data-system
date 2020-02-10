package com.sensesnet.rest;

import com.sensesnet.model.User;
import com.sensesnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * Rest
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController
{
    private final static Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public ResponseEntity<User> getTaskById(@RequestBody User user) {
        log.info("user REST user id:{}", user.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getById(User.class, user.getUserId()));
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getTaskById() {
        log.info("user REST user list:{}", userService.getAll());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getAll());
    }
}

/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.rest;

import com.travelwith.servertravelwith.user.model.UserCreationStatus;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    static final String REGISTRATION_URL = "/users/register";

    private static final String LOGIN_URL = "/users/login";

    @Autowired
    private UserService userService;

    @PostMapping(REGISTRATION_URL)
    public ResponseEntity<UserCreationStatus> createUser(@Valid @RequestBody UserRest requestedUser) {
        try {
            return ResponseEntity.ok(userService.registerUser(requestedUser));
        } catch (Exception e) {
            log.error("Cannot create user", e);
            return ResponseEntity.status(500).body(UserCreationStatus.error("User creation failed"));
        }
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity logIn(@RequestHeader String auth, HttpServletResponse response) {
        if (userService.logIn(auth)) {
            response.addCookie(new Cookie("key", "value"));
            // todo managing cookies
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }


}

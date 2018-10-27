/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.Data;

@Data
public class UserRest {

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;

    public static User createUser(UserRest requestedUser) {
        User user = new User();
        user.setEmail(requestedUser.getEmail());
        user.setUserName(requestedUser.getUserName());
        user.setPassword(requestedUser.getPassword());
        user.setPhoneNumber(requestedUser.getPhoneNumber());
        return user;
    }
}

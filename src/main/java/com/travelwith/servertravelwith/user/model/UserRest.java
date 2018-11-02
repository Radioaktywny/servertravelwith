/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRest {

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;

    public static User createUserEntity(UserRest requestedUser) {
        User user = new User();
        user.setEmail(requestedUser.getEmail());
        user.setUserName(requestedUser.getUserName());
        user.setPassword(requestedUser.getPassword());
        user.setPhoneNumber(requestedUser.getPhoneNumber());
        return user;
    }
}

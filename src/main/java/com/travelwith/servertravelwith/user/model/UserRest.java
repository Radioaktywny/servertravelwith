/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRest {

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;

    public static UserEntity createUserEntity(UserRest requestedUser) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(requestedUser.getEmail());
        userEntity.setUserName(requestedUser.getUserName());
        userEntity.setPassword(requestedUser.getPassword());
        userEntity.setPhoneNumber(requestedUser.getPhoneNumber());
        return userEntity;
    }
}

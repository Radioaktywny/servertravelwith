/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.service;

import com.travelwith.servertravelwith.user.model.UserCreationStatus;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreationStatus registerUser(UserRest requestedUser) {
        userRepository.save(UserRest.createUserEntity(requestedUser));
        return UserCreationStatus.ok();
    }

    public boolean logIn(String auth) {
        log.info("incoming auth {}", auth);
        return true;
    }
}

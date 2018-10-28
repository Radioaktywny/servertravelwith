/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.service;

import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.repository.UserRepository;

public class UserValidatorService {

    private final UserRepository userRepository;

    public UserValidatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserNameValid(UserRest requestedUser) {
        if (requestedUser.getUserName() == null || requestedUser.getUserName().isEmpty()) {
            return false;
        }
        return userRepository.findAllByUserName(requestedUser.getUserName()).isEmpty();
    }

    public boolean isEmailValid(UserRest requestedUser) {
        if (requestedUser.getEmail() == null || requestedUser.getEmail().isEmpty()) {
            return false;
        }
        return userRepository.findAllByEmail(requestedUser.getEmail()).isEmpty();
    }

    public boolean isPhoneNumberValid(UserRest requestedUser) {
        if (requestedUser.getPhoneNumber() == null || requestedUser.getPhoneNumber().isEmpty()) {
            return false;
        }
        return userRepository.findAllByPhoneNumber(requestedUser.getPhoneNumber()).isEmpty();
    }

}

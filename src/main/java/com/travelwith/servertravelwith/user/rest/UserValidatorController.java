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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserValidatorController {

    public static final String CHECK_EMAIL_URL = "/users/check/email";
    public static final String CHECK_USERNAME_URL = "/users/check/username";
    public static final String CHECK_PHONE_URL = "/users/check/phonenumber";

    @Autowired
    private UserService userService;

    @PostMapping(CHECK_USERNAME_URL)
    public ResponseEntity isUsernameValid(@Valid @RequestBody UserRest requestedUser) {
        if (requestedUser.getUserName() == null) {
            return ResponseEntity.status(500).build();
        }
        return createResponseForValidation(userService.isUserNameValid(requestedUser));
    }

    @PostMapping(CHECK_EMAIL_URL)
    public ResponseEntity<UserCreationStatus> isEmailValid(@Valid @RequestBody UserRest requestedUser) {
        if (requestedUser.getEmail() == null) {
            return ResponseEntity.status(500).build();
        }
        return createResponseForValidation(userService.isEmailValid(requestedUser));
    }

    @PostMapping(CHECK_PHONE_URL)
    public ResponseEntity<UserCreationStatus> isPhoneNumberValid(@Valid @RequestBody UserRest requestedUser) {
        if (requestedUser.getPhoneNumber() == null) {
            return ResponseEntity.status(500).build();
        }
        return createResponseForValidation(userService.isPhoneNumberValid(requestedUser));
    }

    private ResponseEntity<UserCreationStatus> createResponseForValidation(boolean isValid) {
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }


}

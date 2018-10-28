/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.rest;

import com.travelwith.servertravelwith.user.model.UserCreationStatus;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.service.UserValidatorService;
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

    static final String CHECK_EMAIL_URL = "/users/check/email";
    static final String CHECK_USERNAME_URL = "/users/check/username";
    static final String CHECK_PHONE_URL = "/users/check/phonenumber";

    @Autowired
    private UserValidatorService userValidatorService;

    @PostMapping(CHECK_USERNAME_URL)
    public ResponseEntity isUsernameValid(@Valid @RequestBody UserRest requestedUser) {
        return createResponseForValidation(userValidatorService.isUserNameValid(requestedUser));
    }

    @PostMapping(CHECK_EMAIL_URL)
    public ResponseEntity<UserCreationStatus> isEmailValid(@Valid @RequestBody UserRest requestedUser) {
        return createResponseForValidation(userValidatorService.isEmailValid(requestedUser));
    }

    @PostMapping(CHECK_PHONE_URL)
    public ResponseEntity<UserCreationStatus> isPhoneNumberValid(@Valid @RequestBody UserRest requestedUser) {
        return createResponseForValidation(userValidatorService.isPhoneNumberValid(requestedUser));
    }

    private ResponseEntity<UserCreationStatus> createResponseForValidation(boolean isValid) {
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }


}

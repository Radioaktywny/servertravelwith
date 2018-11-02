/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.Data;

@Data
public class UserCreationStatus {

    private String errorMessage;

    public static UserCreationStatus ok() {
        return new UserCreationStatus();
    }

    public static UserCreationStatus error(String errorMessage) {
        UserCreationStatus status = new UserCreationStatus();
        status.setErrorMessage(errorMessage);
        return status;
    }
}

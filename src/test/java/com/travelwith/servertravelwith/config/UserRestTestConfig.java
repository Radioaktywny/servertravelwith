/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.config;

import com.travelwith.servertravelwith.user.service.UserService;
import com.travelwith.servertravelwith.user.service.UserValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class UserRestTestConfig {

    @Bean
    UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    UserValidatorService userValidatorService() {
        return mock(UserValidatorService.class);
    }


}

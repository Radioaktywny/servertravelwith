/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user;

import com.travelwith.servertravelwith.user.repository.UserRepository;
import com.travelwith.servertravelwith.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

}


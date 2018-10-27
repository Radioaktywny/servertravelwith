/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.config;

import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class UserRestTestConfig {

    @Bean
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }


}

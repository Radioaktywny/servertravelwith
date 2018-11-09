/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.security;

import com.travelwith.servertravelwith.user.model.Role;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.travelwith.servertravelwith.user.rest.UserController.LOGIN_URL;
import static com.travelwith.servertravelwith.user.rest.UserController.REGISTRATION_URL;
import static com.travelwith.servertravelwith.user.rest.UserValidatorController.VALIDATION_CONTROLLER_PREFIX_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userDetailsManager(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(REGISTRATION_URL).hasRole((Role.ADMIN.getRoleName()))
                .antMatchers(VALIDATION_CONTROLLER_PREFIX_URL + "/**").hasRole((Role.ADMIN.getRoleName()))
                .antMatchers(LOGIN_URL).hasRole(Role.USER.getRoleName())
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.service;


import com.travelwith.servertravelwith.user.model.UserEntity;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserEntityValidatorServiceTest {

    private UserValidatorService userService;

    private UserRepository userRepository;

    @Before
    public void initMocks() {
        userRepository = mock(UserRepository.class);
        userService = new UserValidatorService(userRepository);
    }

    @Test
    public void emptyUsernameIsIncorrect() {
        assertThat(userService.isUserNameValid(userWithUsername(""))).isFalse();
        assertThat(userService.isUserNameValid(userWithUsername(null))).isFalse();
    }

    @Test
    public void emptyEmailIsIncorrect() {
        assertThat(userService.isEmailValid(userWithEmail(""))).isFalse();
        assertThat(userService.isEmailValid(userWithEmail(null))).isFalse();
    }

    @Test
    public void emptyPhoneIsIncorrect() {
        assertThat(userService.isPhoneNumberValid(userWithPhone(""))).isFalse();
        assertThat(userService.isPhoneNumberValid(userWithPhone(null))).isFalse();
    }

    @Test
    public void whenThereIsUserWithTheSamePropertyThenPropertyIsIncorrect() {
        when(userRepository.findAllByUserName(any())).thenReturn(Collections.singletonList(new UserEntity()));
        when(userRepository.findAllByEmail(any())).thenReturn(Collections.singletonList(new UserEntity()));
        when(userRepository.findAllByPhoneNumber(any())).thenReturn(Collections.singletonList(new UserEntity()));

        assertThat(userService.isUserNameValid(userWithUsername("username"))).isFalse();
        assertThat(userService.isEmailValid(userWithEmail("email@email.com"))).isFalse();
        assertThat(userService.isPhoneNumberValid(userWithPhone("123456789"))).isFalse();
    }

    @Test
    public void whenThereIsNoUserWithTheSamePropertyThenPropertyIsCorrect() {
        when(userRepository.findAllByUserName(any())).thenReturn(Collections.emptyList());
        when(userRepository.findAllByEmail(any())).thenReturn(Collections.emptyList());
        when(userRepository.findAllByPhoneNumber(any())).thenReturn(Collections.emptyList());

        assertThat(userService.isUserNameValid(userWithUsername("username"))).isTrue();
        assertThat(userService.isEmailValid(userWithEmail("email@email.com"))).isTrue();
        assertThat(userService.isPhoneNumberValid(userWithPhone("123456789"))).isTrue();
    }


    private UserRest userWithUsername(String username) {
        return UserRest.builder().userName(username).build();
    }

    private UserRest userWithEmail(String email) {
        return UserRest.builder().email(email).build();
    }

    private UserRest userWithPhone(String phone) {
        return UserRest.builder().phoneNumber(phone).build();

    }
}

/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.rest;


import com.travelwith.servertravelwith.config.HttpSecurityConfiguration;
import com.travelwith.servertravelwith.config.UserRestTestConfig;
import com.travelwith.servertravelwith.user.UserConfiguration;
import com.travelwith.servertravelwith.user.model.User;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

import static com.travelwith.servertravelwith.user.rest.UserValidatorController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserConfiguration.class, UserRestTestConfig.class, UserValidatorController.class, HttpSecurityConfiguration.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@AutoConfigureMockMvc
public class UserValidatorControllerTest {

    private static final String CONTENT_WITH_INVALID_JSON = "{ \"someIncorrect\" : \"incorrect\"  }";

    private static final String CONTENT_EMAIL = "{ \"email\" : \"some@gmail.com\"  }";

    private static final String CONTENT_USER_NAME = "{ \"userName\" : \"name\"  }";

    private static final String CONTENT_PHONE_NUMBER = "{ \"phoneNumber\" : \"123456789\"  }";
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenThereIsInvalidJsonForCheckingThenReturns500() throws Exception {
        performRequest(CHECK_EMAIL_URL, CONTENT_WITH_INVALID_JSON).andExpect(status().is5xxServerError());
        performRequest(CHECK_USERNAME_URL, CONTENT_WITH_INVALID_JSON).andExpect(status().is5xxServerError());
        performRequest(CHECK_PHONE_URL, CONTENT_WITH_INVALID_JSON).andExpect(status().is5xxServerError());
    }

    @Test
    public void whenThereIsNoUserWithProvidedEmailThenReturns200x() throws Exception {
        when(userRepository.findAllByEmail(any())).thenReturn(Collections.emptyList());
        performRequest(CHECK_EMAIL_URL, CONTENT_EMAIL).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenThereIsNoUserWithProvidedPhoneThenReturns200x() throws Exception {
        when(userRepository.findAllByPhoneNumber(any())).thenReturn(Collections.emptyList());
        performRequest(CHECK_PHONE_URL, CONTENT_PHONE_NUMBER).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenThereIsNoUserWithProvidedNameThenReturns200x() throws Exception {
        when(userRepository.findAllByUserName(any())).thenReturn(Collections.emptyList());
        performRequest(CHECK_USERNAME_URL, CONTENT_USER_NAME).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenThereIsAlreadyUserWithProvidedEmailThenReturns200x() throws Exception {
        when(userRepository.findAllByEmail(any())).thenReturn(Collections.singletonList(new User()));
        performRequest(CHECK_EMAIL_URL, CONTENT_EMAIL).andExpect(status().is5xxServerError());
    }

    @Test
    public void whenThereIsAlreadyUserWithProvidedPhoneNumberThenReturns200x() throws Exception {
        when(userRepository.findAllByPhoneNumber(any())).thenReturn(Collections.singletonList(new User()));
        performRequest(CHECK_PHONE_URL, CONTENT_PHONE_NUMBER).andExpect(status().is5xxServerError());
    }

    @Test
    public void whenThereIsAlreadyUserWithProvidedNameThenReturns200x() throws Exception {
        when(userRepository.findAllByUserName(any())).thenReturn(Collections.singletonList(new User()));
        performRequest(CHECK_USERNAME_URL, CONTENT_USER_NAME).andExpect(status().is5xxServerError());
    }

    private ResultActions performRequest(String url, String content) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("utf-8"));
    }

}
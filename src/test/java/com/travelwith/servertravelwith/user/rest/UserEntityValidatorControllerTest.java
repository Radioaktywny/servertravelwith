/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.rest;


import com.travelwith.servertravelwith.config.HttpSecurityConfiguration;
import com.travelwith.servertravelwith.config.UserRestTestConfig;
import com.travelwith.servertravelwith.user.service.UserValidatorService;
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

import static com.travelwith.servertravelwith.user.rest.UserValidatorController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserRestTestConfig.class, UserValidatorController.class, HttpSecurityConfiguration.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@AutoConfigureMockMvc
public class UserEntityValidatorControllerTest {

    @Autowired
    private UserValidatorService userValidatorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenThereIsInvalidPropertyThenReturns500() throws Exception {
        when(userValidatorService.isEmailValid(any())).thenReturn(false);
        when(userValidatorService.isUserNameValid(any())).thenReturn(false);
        when(userValidatorService.isPhoneNumberValid(any())).thenReturn(false);

        performRequest(CHECK_EMAIL_URL).andExpect(status().is5xxServerError());
        performRequest(CHECK_USERNAME_URL).andExpect(status().is5xxServerError());
        performRequest(CHECK_PHONE_URL).andExpect(status().is5xxServerError());
    }

    @Test
    public void whenThereIsValidPropertyThenReturns200() throws Exception {
        when(userValidatorService.isEmailValid(any())).thenReturn(true);
        when(userValidatorService.isUserNameValid(any())).thenReturn(true);
        when(userValidatorService.isPhoneNumberValid(any())).thenReturn(true);

        performRequest(CHECK_EMAIL_URL).andExpect(status().is2xxSuccessful());
        performRequest(CHECK_USERNAME_URL).andExpect(status().is2xxSuccessful());
        performRequest(CHECK_PHONE_URL).andExpect(status().is2xxSuccessful());
    }

    private ResultActions performRequest(String url) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .characterEncoding("utf-8"));
    }

}
/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.rest;

import com.travelwith.servertravelwith.config.HttpSecurityConfiguration;
import com.travelwith.servertravelwith.config.UserRestTestConfig;
import com.travelwith.servertravelwith.user.model.UserCreationStatus;
import com.travelwith.servertravelwith.user.service.UserService;
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

import static com.travelwith.servertravelwith.user.rest.UserController.REGISTRATION_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserRestTestConfig.class, UserController.class, HttpSecurityConfiguration.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@AutoConfigureMockMvc
public class UserRegistrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenCannotRegisterUserThenReturns500() throws Exception {
        when(userService.registerUser(any())).thenThrow(new IllegalArgumentException());
        performRequest(REGISTRATION_URL, "{}").andExpect(status().is5xxServerError());
    }

    @Test
    public void whenRegisterUserThenReturns200() throws Exception {
        when(userService.registerUser(any())).thenReturn(new UserCreationStatus());
        performRequest(REGISTRATION_URL, "{}").andExpect(status().is2xxSuccessful());
    }

    private ResultActions performRequest(String url, String content) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("utf-8"));
    }
}

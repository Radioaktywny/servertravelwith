package com.travelwith.servertravelwith.user.service;

import com.travelwith.servertravelwith.user.model.Role;
import com.travelwith.servertravelwith.user.model.UserEntity;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Marcin on 04.11.2018.
 */
@SpringBootTest(classes = {UserService.class})
@RunWith(SpringRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<UserEntity> captor;

    @Autowired
    private UserService userService;

    @Test
    public void aPostConstructTriedToCreateAdminUser() {
        //given when creating bean
        verify(userRepository, times(1)).findAllByUserName("AndroidClient");
        verify(userRepository, times(1)).save(captor.capture());
        assertThat(captor.getValue())
                .extracting("userName", "password", "role")
                .contains("AndroidClient", "s6NY&<u)<asP+nUr", Role.ADMIN);
    }

    @Test
    public void bWhenRegisterUserThenSavesExpectedUser() {
        //given
        reset(userRepository);
        //when
        userService.registerUser(createExampleUser());
        //then
        verify(userRepository, times(1)).save(captor.capture());
        assertThat(captor.getValue())
                .extracting("userName", "password", "role", "email")
                .contains("userName", "password", Role.USER, "email@email");
    }

    private UserRest createExampleUser() {
        return new UserRest("userName", "password", "email@email", null);
    }


}

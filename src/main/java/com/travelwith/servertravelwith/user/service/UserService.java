/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.service;

import com.travelwith.servertravelwith.user.model.Role;
import com.travelwith.servertravelwith.user.model.UserCreationStatus;
import com.travelwith.servertravelwith.user.model.UserEntity;
import com.travelwith.servertravelwith.user.model.UserRest;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
public class UserService {

    private static final String ANDROID_CLIENT_USERNAME = "AndroidClient";
    private static final String ANDROID_CLIENT_PASSWORD = "s6NY&<u)<asP+nUr";
    private static final String ANDROID_CLIENT_EMAIL = "this@is.email";
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    void initAndroidAdminUser() {
        Optional<UserEntity> user = userRepository.findAllByUserName(ANDROID_CLIENT_USERNAME).stream().findAny();
        if (user.isPresent()) {
            saveUserWithAdminValues(user.get());
        } else {
            saveUserWithAdminValues(new UserEntity());
        }
    }

    private void saveUserWithAdminValues(UserEntity userEntity) {
        userEntity.setUserName(ANDROID_CLIENT_USERNAME);
        userEntity.setEmail(ANDROID_CLIENT_EMAIL);
        userEntity.setPassword(ANDROID_CLIENT_PASSWORD);
        userEntity.setRole(Role.ADMIN);
        userRepository.save(userEntity);
    }

    public UserCreationStatus registerUser(UserRest requestedUser) {
        UserEntity userEntityEntity = UserRest.createUserEntity(requestedUser);
        userRepository.save(userEntityEntity);
        return UserCreationStatus.ok();
    }

}

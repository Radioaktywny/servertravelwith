package com.travelwith.servertravelwith.security;

import com.travelwith.servertravelwith.user.model.UserEntity;
import com.travelwith.servertravelwith.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Marcin on 03.11.2018.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final static String NO_OP_PASSWORD_ENCODER_PREFIX = "{noop}";

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = getUserEntityFromRepository(userName);

        return User
                .withUsername(userEntity.getUserName())
                .password(NO_OP_PASSWORD_ENCODER_PREFIX + userEntity.getPassword())
                .roles(userEntity.getRole().getRoleName()).build();
    }

    private UserEntity getUserEntityFromRepository(String userName) {
        List<UserEntity> userEntityEntityList = userRepository.findAllByUserName(userName);
        Assert.notEmpty(userEntityEntityList, "Cannot find user with provided username :" + userName);
        return userEntityEntityList.get(0);
    }
}

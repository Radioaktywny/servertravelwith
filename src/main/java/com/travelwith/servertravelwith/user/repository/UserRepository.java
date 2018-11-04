/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.repository;


import com.travelwith.servertravelwith.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUserName(String userName);

    List<UserEntity> findAllByEmail(String email);

    List<UserEntity> findAllByPhoneNumber(String userName);

}

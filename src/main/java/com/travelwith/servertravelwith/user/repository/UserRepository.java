/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.repository;


import com.travelwith.servertravelwith.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserName(String userName);

    List<User> findAllByEmail(String email);

    List<User> findAllByPhoneNumber(String userName);

}

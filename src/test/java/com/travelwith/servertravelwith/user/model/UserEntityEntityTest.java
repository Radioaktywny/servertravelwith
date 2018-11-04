/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityEntityTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void phoneNumberFiledIsNotRequired() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setPhoneNumber(null);
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).isEmpty();
    }

    @Test
    public void userNameIsRequired() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setUserName(null);
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }


    @Test
    public void emailIsRequired() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setEmail(null);
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void passwordIsRequired() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setPassword(null);
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void userNameCannotBeEmpty() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setUserName("");
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void emailCannotBeEmpty() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setEmail("");
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void passwordCannotBeEmpty() {
        UserEntity userEntityToInsert = createValidUserEntity();
        userEntityToInsert.setPassword("");
        //when
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntityToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    private UserEntity createValidUserEntity() {
        return UserRest.createUserEntity(UserRest.builder()
                .email("email@email")
                .userName("username")
                .password("password")
                .phoneNumber("1233223").build());
    }
}
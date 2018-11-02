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

public class UserEntityTest {

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
        User userToInsert = createValidUserEntity();
        userToInsert.setPhoneNumber(null);
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).isEmpty();
    }

    @Test
    public void userNameIsRequired() {
        User userToInsert = createValidUserEntity();
        userToInsert.setUserName(null);
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }


    @Test
    public void emailIsRequired() {
        User userToInsert = createValidUserEntity();
        userToInsert.setEmail(null);
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void passwordIsRequired() {
        User userToInsert = createValidUserEntity();
        userToInsert.setPassword(null);
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void userNameCannotBeEmpty() {
        User userToInsert = createValidUserEntity();
        userToInsert.setUserName("");
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void emailCannotBeEmpty() {
        User userToInsert = createValidUserEntity();
        userToInsert.setEmail("");
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    @Test
    public void passwordCannotBeEmpty() {
        User userToInsert = createValidUserEntity();
        userToInsert.setPassword("");
        //when
        Set<ConstraintViolation<User>> violations = validator.validate(userToInsert);
        //then
        assertThat(violations).hasSize(1);
    }

    private User createValidUserEntity() {
        return UserRest.createUserEntity(UserRest.builder()
                .email("email@email")
                .userName("username")
                .password("password")
                .phoneNumber("1233223").build());
    }
}
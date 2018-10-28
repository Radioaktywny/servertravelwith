/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.repository;

import com.travelwith.servertravelwith.user.model.User;
import com.travelwith.servertravelwith.user.model.UserRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityUniquesTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Test(expected = Exception.class)
    public void whenTryingToAddUserWithNotUniqueUserNameThenThrowsException() {
        testEntityManager.persistAndFlush(createUser("userName", "email@first", "pass", "2222222"));
        testEntityManager.persistAndFlush(createUser("userName", "email@inny", "pass1234", "1111111"));
    }

    @Test(expected = Exception.class)
    public void whenTryingToAddUserWithNotUniqueEmailThenThrowsException() {
        testEntityManager.persistAndFlush(createUser("userName1", "email@thesame", "pass", "12345679"));
        testEntityManager.persistAndFlush(createUser("userName2", "email@thesame", "pass1234", "12345679"));
    }

    @Test
    public void whenTryingToAddUserWithUniqueFieldsThenWillBeAddedSuccessfully() {
        testEntityManager.persistAndFlush(createUser("userName1", "email@first", "pass", "12345679"));
        final User userToInsert = createUser("userName2", "email@second", "pass1234", "12345679");
        User inserted = testEntityManager.persistAndFlush(userToInsert);
        assertUserFields(inserted, userToInsert);
    }

    private void assertUserFields(User inserted, User userToInsert) {
        assertThat(inserted.getEmail()).isEqualToIgnoringCase(userToInsert.getEmail());
        assertThat(inserted.getPassword()).isEqualToIgnoringCase(userToInsert.getPassword());
        assertThat(inserted.getUserName()).isEqualToIgnoringCase(userToInsert.getUserName());
        assertThat(inserted.getPhoneNumber()).isEqualToIgnoringCase(userToInsert.getPhoneNumber());
        assertThat(inserted.getId()).isGreaterThan(0L);
    }

    private User createUser(String userName, String email, String password, String phone) {
        return UserRest.createUserEntity(UserRest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .phoneNumber(phone).build());
    }

}

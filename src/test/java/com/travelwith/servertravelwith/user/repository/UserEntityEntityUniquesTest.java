/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.repository;

import com.travelwith.servertravelwith.user.model.UserEntity;
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
public class UserEntityEntityUniquesTest {

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
        final UserEntity userEntityToInsert = createUser("userName2", "email@second", "pass1234", "12345679");
        UserEntity inserted = testEntityManager.persistAndFlush(userEntityToInsert);
        assertUserFields(inserted, userEntityToInsert);
    }

    private void assertUserFields(UserEntity inserted, UserEntity userEntityToInsert) {
        assertThat(inserted.getEmail()).isEqualToIgnoringCase(userEntityToInsert.getEmail());
        assertThat(inserted.getPassword()).isEqualToIgnoringCase(userEntityToInsert.getPassword());
        assertThat(inserted.getUserName()).isEqualToIgnoringCase(userEntityToInsert.getUserName());
        assertThat(inserted.getPhoneNumber()).isEqualToIgnoringCase(userEntityToInsert.getPhoneNumber());
        assertThat(inserted.getId()).isGreaterThan(0L);
    }

    private UserEntity createUser(String userName, String email, String password, String phone) {
        return UserRest.createUserEntity(UserRest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .phoneNumber(phone).build());
    }

}

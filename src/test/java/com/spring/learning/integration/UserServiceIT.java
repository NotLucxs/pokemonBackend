package com.spring.learning.integration;

import com.spring.learning.users.UserProfile;
import com.spring.learning.users.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceIT {

    //Services
    @Autowired
    private UserService userService;
    //Data
    private String testUsername = "testUser";
    private UserProfile user1 = UserProfile.builder().username(testUsername).firstName("test").lastName("user").age(87).createdDate(Instant.now()).build();
    private UserProfile user2 = UserProfile.builder().username(testUsername+"2").firstName("test2").lastName("user2").age(43).createdDate(Instant.now()).build();

    @AfterEach
    void deleteUsers() {
        var res = userService.getAllUserProfiles();
        res.forEach(userService::deleteUser);
    }

    @Test
    void testCreateUser() {
        // GIVEN
        userService.createUser(user1);

        // WHEN
        var res = userService.getUserProfile(testUsername);

        // THEN
        Assertions.assertEquals(user1.getUsername(), testUsername);
    }

    @Test
    void testGetAllUsers() {
        //GIVEN
        userService.createUser(user1);
        userService.createUser(user2);

        // WHEN
        var res = userService.getAllUserProfiles();

        // THEN
        Assertions.assertEquals(2, res.size());
    }

    @Test
    void testDeleteUsers() {
        // GIVEN
        userService.createUser(user1);
        userService.deleteUser(user1);

        // WHEN
        var res = userService.getUserProfile(user1.getUsername());

        // THEN
        Assertions.assertNull(res);
    }
}

package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.dao.UserDao;
import com.learning.simple_e_commerce.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserDaoImplIntegrationTests {

    UserDao userDao;

    @Autowired
    public UserDaoImplIntegrationTests(UserDao userDao){
        this.userDao = userDao;
}

    @Test
    public void testUserCreatedAndRetrieved(){
        User user = UserDaoImpl.buildExempleUser();

        userDao.create(user);
        Optional<User> userRetrieved = userDao.find(user.getUser_id());

        //test a user found
        assertTrue(userRetrieved.isPresent(), "User not found");

        //test the correct user is retrieved
        assertEquals(user, userRetrieved.get(), "User retrieved but not the one expected");
    }
}

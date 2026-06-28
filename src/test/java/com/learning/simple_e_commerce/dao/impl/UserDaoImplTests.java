package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.entity.User;
import com.learning.simple_e_commerce.util.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDaoImpl userDao;

    @Test
    //Test that the method create is generating a correct SQL query to insert a user into the database
    public void testCreateUserGenerateCorrectSql(){
        //create a user to transform into sql query
        User user = UserDaoImpl.buildExempleUser();

        //call the method under test
        userDao.create(user);

        //verify that the correct sql query was generated
        String sqlQuery = "INSERT INTO users (first_name, last_name, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        verify(jdbcTemplate).update(sqlQuery,
                "John",
                "Doe",
                "mail@hotmail.com",
                "1234567890",
                "123 Main St",
                Role.USER.toString()
        );
    }


    @Test
    public void testFindOneUserGenerateCorrectSql(){
        User user = UserDaoImpl.buildExempleUser();

        // Method under test
        userDao.find(user.getUser_id());

        // Verify method is called
        String sqlToSelect  = "user_id, first_name, last_name, email, phone, address, role";
        String sqlQuery= "SELECT ? FROM Users WHERE user_id = ? LIMIT 1";
        verify(jdbcTemplate).queryForObject(
                eq(sqlQuery),
                any(User.UserRowMapper.class),
                eq(sqlToSelect),
                eq(user.getUser_id())
        );
    }
}

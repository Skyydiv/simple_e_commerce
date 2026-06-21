package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.entity.User;
import com.learning.simple_e_commerce.util.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    //Test that the method create of the DAO is generating a correct SQL query from a User object to be inserted into the database
    public void testCreateUserGenerateCorrectSql(){
        //create a user to transform into sql query
        User user = User.builder()
                .user_id(1L)
                .first_name("John")
                .last_name("Doe")
                .email("mail@hotmail.com")
                .phone("1234567890")
                .address("123 Main St")
                .role(Role.USER)
                .build();

        //call the method under test
        userDao.create(user);

        //verify that the correct sql query was generated
        String sqlQuerry = "INSERT INTO Users (user_id, first_name, last_name, email, phone, address, role) VALUES (?,?,?,?,?,?,?)";
        verify(jdbcTemplate).update(sqlQuerry,
                1L,
                "John",
                "Doe",
                "mail@hotmail.com",
                "1234567890",
                "123 Main St",
                Role.USER.toString()
        );
    }
}

package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.dao.UserDao;
import com.learning.simple_e_commerce.entity.User;
import com.learning.simple_e_commerce.util.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        String sql_query = "INSERT INTO Users (user_id, first_name, last_name, email, phone, address, role) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql_query,
                user.getUser_id(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getRole().toString()
        );
    }

    @Override
    public Optional<User> find(Long userId) {
        String sqlToSelect  = "user_id, first_name, last_name, email, phone, address, role";
        String sqlQuery= "SELECT ? FROM Users WHERE user_id = ? LIMIT 1";

        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    sqlQuery,
                    new User.UserRowMapper(),
                    sqlToSelect,
                    userId
                )
        );
    }

    public static User buildExempleUser(){
        return User.builder()
                .user_id(1L)
                .first_name("John")
                .last_name("Doe")
                .email("mail@hotmail.com")
                .phone("1234567890")
                .address("123 Main St")
                .role(Role.USER)
                .build();
    }
}

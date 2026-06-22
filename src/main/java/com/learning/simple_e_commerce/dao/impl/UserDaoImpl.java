package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.dao.UserDao;
import com.learning.simple_e_commerce.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

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
    public User findOne(Long userId) {
        String sqlQuery = "SELECT * FROM users WHERE user_id = ? LIMIT 1";
        return jdbcTemplate.queryForObject(
                sqlQuery,
                new User.UserRowMapper(),
                userId
        );
    }
}

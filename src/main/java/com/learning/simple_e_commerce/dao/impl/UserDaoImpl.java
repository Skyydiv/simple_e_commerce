package com.learning.simple_e_commerce.dao.impl;

import com.learning.simple_e_commerce.dao.UserDao;
import com.learning.simple_e_commerce.entity.User;
import com.learning.simple_e_commerce.util.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        String sql_query = "INSERT INTO users (first_name, last_name, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_query,
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

        String sqlQuery= "SELECT user_id, first_name, last_name, email, phone, address, role FROM Users WHERE user_id = ? LIMIT 1";

        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    sqlQuery,
                    new UserRowMapper(),
                    userId
                )
        );
    }

    public static User buildExempleUser(Role role){
        return User.builder()
                .user_id(1L)
                .first_name("John")
                .last_name("Doe")
                .email("mail@hotmail.com")
                .phone("1234567890")
                .address("123 Main St")
                .role(role)
                .build();
    }

    public static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    Role.valueOf(
                           rs.getString("role").toUpperCase()
                    )
            );
        }
    }
}

package com.learning.simple_e_commerce.entity;

import com.learning.simple_e_commerce.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private Role role;



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

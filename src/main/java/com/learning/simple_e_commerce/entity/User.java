package com.learning.simple_e_commerce.entity;

import com.learning.simple_e_commerce.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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


    public static User buildExempleUser(Role role){
        return builder()
                .user_id(1L)
                .first_name("John")
                .last_name("Doe")
                .email("mail@hotmail.com")
                .phone("1234567890")
                .address("123 Main St")
                .role(role)
                .build();
    }

    public static List<User> buildExempleUserList(){
        return List.of(
                buildExempleUser(Role.ADMIN),
                builder()
                        .user_id(2L)
                        .first_name("Jane")
                        .last_name("Smith")
                        .email("jane.smith@hotmail.com")
                        .phone("1234567891")
                        .address("124 Main St")
                        .role(Role.USER)
                        .build(),
                builder()
                        .user_id(3L)
                        .first_name("Bob")
                        .last_name("Brown")
                        .email("bob.brown@hotmail.com")
                        .phone("1234567892")
                        .address("125 Main St")
                        .role(Role.USER)
                        .build(),
                builder()
                        .user_id(4L)
                        .first_name("Alice")
                        .last_name("Green")
                        .email("alice.green@hotmail.com")
                        .phone("1234567893")
                        .address("126 Main St")
                        .role(Role.USER)
                        .build()
        );
    }
}

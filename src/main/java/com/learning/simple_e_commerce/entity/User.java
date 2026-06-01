package com.learning.simple_e_commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private String role;
}

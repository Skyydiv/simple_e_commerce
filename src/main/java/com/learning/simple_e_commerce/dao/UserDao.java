package com.learning.simple_e_commerce.dao;

import com.learning.simple_e_commerce.entity.User;

import java.util.Optional;

public interface UserDao {
    void create(User user);

    Optional<User> findOne(Long userId);
}

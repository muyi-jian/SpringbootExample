package com.fincodehub.jpa.service;


import com.fincodehub.jpa.entity.User;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserService
 * @create 2025/5/8 21:25
 * @description <TODO description class purpose>
 */
public interface UserService {
    List<User> findAllUsers();

    User saveUser(User user);

    List<User> findByUsername(String username);

    List<User> findByEmailLike(String keyword);

    User findById(Long id);

    void save(User entity);
}

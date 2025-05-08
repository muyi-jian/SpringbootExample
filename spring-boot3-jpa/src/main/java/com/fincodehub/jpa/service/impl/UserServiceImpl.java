package com.fincodehub.jpa.service.impl;


import com.fincodehub.jpa.entity.User;
import com.fincodehub.jpa.repository.UserRepository;
import com.fincodehub.jpa.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserServiceImpl
 * @create 2025/5/8 21:26
 * @description <TODO description class purpose>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findByEmailLike(String keyword) {
        return userRepository.findByEmailLike(keyword);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("未找到对应的数据"));
    }

    @Override
    public void save(User entity) {
        userRepository.save(entity);
    }

}

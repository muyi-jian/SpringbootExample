package com.fincodehub.mybatis.service.impl;


import com.fincodehub.mybatis.entity.User;
import com.fincodehub.mybatis.mapper.test1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserService
 * @create 2025/5/12 10:19
 * @description <TODO description class purpose>
 */
@Service
public class UserService implements com.fincodehub.mybatis.service.UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        System.out.println("test1:::::::::"+id);
        User userById = userMapper.getUserById(id);
        return userById;
    }
}

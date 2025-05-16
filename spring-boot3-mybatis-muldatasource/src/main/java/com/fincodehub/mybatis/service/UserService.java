package com.fincodehub.mybatis.service;


import com.fincodehub.mybatis.entity.User;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserService
 * @create 2025/5/12 10:19
 * @description <TODO description class purpose>
 */
public interface UserService {
    User getUserById(Integer id);

}

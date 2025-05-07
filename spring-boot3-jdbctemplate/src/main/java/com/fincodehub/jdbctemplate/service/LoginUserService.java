package com.fincodehub.jdbctemplate.service;


import com.fincodehub.jdbctemplate.entity.LoginUser;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserService
 * @create 2025/5/7 21:47
 * @description <TODO description class purpose>
 */
public interface LoginUserService {
    List<LoginUser> queryLoginUser();
    void save(LoginUser user);
    void update(LoginUser user);
    void deleteById(Integer id);
}

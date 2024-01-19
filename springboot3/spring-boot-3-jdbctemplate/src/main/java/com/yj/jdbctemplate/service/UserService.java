package com.yj.jdbctemplate.service;

import com.yj.jdbctemplate.entity.User;

import java.util.List;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/18 20:59
 */
public interface UserService {
    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUser(Long id);
    public List<User> getAll();
}

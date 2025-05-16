package com.fincodehub.mybatis.controller;


import com.fincodehub.mybatis.entity.User;
import com.fincodehub.mybatis.entity.Userinfo;
import com.fincodehub.mybatis.service.UserService;
import com.fincodehub.mybatis.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserController
 * @create 2025/5/12 10:23
 * @description <TODO description class purpose>
 */
@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserinfoService userinfoService;
    @GetMapping("/test1/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/test2/{id}")
    public Userinfo getUserById1(@PathVariable("id") Integer id) {
        return userinfoService.getUserById(id);
    }
}

package com.fincodehub.mybatis.controller;


import com.fincodehub.mybatis.entity.LoginUser;
import com.fincodehub.mybatis.mapper.LoginUserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserController
 * @create 2025/4/29 15:57
 * @description <TODO description class purpose>
 */
@RestController
public class LoginUserController {

    private final LoginUserMapper loginUserMapper;

    public LoginUserController(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }
    @GetMapping("/user/{id}")
    public LoginUser getUserById(@PathVariable("id") Integer id) {
        return loginUserMapper.getUserById(id);
    }
}

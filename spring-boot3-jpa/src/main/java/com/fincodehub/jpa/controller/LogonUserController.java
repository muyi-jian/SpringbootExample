package com.fincodehub.jpa.controller;


import com.fincodehub.jpa.entity.LoginUser;
import com.fincodehub.jpa.repository.LoginUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LogonUserController
 * @create 2025/5/8 16:49
 * @description <TODO description class purpose>
 */
@RestController
public class LogonUserController {


    LoginUserRepository loginUserRepository;

    public LogonUserController(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @GetMapping("/test/{id}")
    public LoginUser findLoginUserById(@PathVariable Integer id) {

        return loginUserRepository.findLoginUserById(id);
    }
}

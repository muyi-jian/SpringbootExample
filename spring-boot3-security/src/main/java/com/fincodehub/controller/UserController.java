package com.fincodehub.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserController
 * @create 2025/6/10 22:23
 * @description <TODO description class purpose>
 */
@RestController
public class UserController {

    @GetMapping("/user/profile")
    @Secured("ROLE_USER")  // 只有 USER 角色才能访问
    public String userProfile() {
        return "User Profile Information";
    }
}
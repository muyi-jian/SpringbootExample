package com.fincodehub.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title AdminController
 * @create 2025/6/10 22:23
 * @description <TODO description class purpose>
 */
@RestController
public class AdminController {

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")  // 只有 ADMIN 角色才能访问
    public String adminDashboard() {
        return "Welcome to Admin Dashboard!";
    }
}
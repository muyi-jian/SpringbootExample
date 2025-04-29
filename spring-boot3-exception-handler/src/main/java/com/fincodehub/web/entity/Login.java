package com.fincodehub.web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @version 1.0.0
 * @title Login
 * @create 2025/4/28 22:57
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
public class Login {
    private String username;
    private String password;
}

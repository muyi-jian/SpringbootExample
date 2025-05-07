package com.fincodehub.jdbctemplate.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUser
 * @create 2025/5/7 21:45
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUser {

    private Integer id;
    private String username;
    private String password;
    private String nickName;
}

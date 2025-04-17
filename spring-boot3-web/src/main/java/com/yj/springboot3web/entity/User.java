package com.yj.springboot3web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/4/16 8:16
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
public class User {
    public String name;
    public Integer age;
}

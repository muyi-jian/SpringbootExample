package com.yj.springboot3actuator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/4/15 10:39
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
}

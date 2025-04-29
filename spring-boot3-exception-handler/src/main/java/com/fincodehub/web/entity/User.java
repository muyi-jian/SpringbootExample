package com.fincodehub.web.entity;


/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/4/28 22:20
 * @description <TODO description class purpose>
 */
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

package com.yj.result.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private  String name;
    private String address;
    private int age;

    public User() {

    }
}

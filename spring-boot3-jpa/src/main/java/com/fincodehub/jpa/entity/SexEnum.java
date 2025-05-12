package com.fincodehub.jpa.entity;


/**
 * @author YangJian
 * @version 1.0.0
 * @title SexEnum
 * @create 2025/5/9 9:36
 * @description <TODO description class purpose>
 */
public enum SexEnum {
    MALE("男"),
    FEMALE("女");

    private String sex;

    SexEnum(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

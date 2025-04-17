package com.yj.springboot3web.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;


/**
 * @author YangJian
 * @version 1.0.0
 * @title MyUser
 * @create 2025/4/16 9:20
 * @description <TODO description class purpose>
 */
@Entity
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;
    //@Id标注该字段为实体类的主键，@GeneratedValue指定主键的生成策略（如自增/序列），二者配合实现由数据库自动生成唯一标识符的功能。
    // 这是JPA规范中实体持久化的基础配置。
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String username;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = true, unique = true, length = 30)
    private String nickName;

    public LoginUser(String username, String password, String nickName) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

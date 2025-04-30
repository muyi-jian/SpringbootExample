package com.fincodehub.mybatis.entity;


/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUser
 * @create 2025/4/29 16:37
 * @description <TODO description class purpose>
 */
public class LoginUser {
    private Integer id;
    private String username;
    private String password;
    private String nickName;

    public LoginUser(Integer id, String username, String password, String nickName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

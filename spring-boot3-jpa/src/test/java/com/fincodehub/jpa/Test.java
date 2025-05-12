package com.fincodehub.jpa;


import com.fincodehub.jpa.entity.LoginUser;
import com.fincodehub.jpa.service.IdGenerateService;
import com.fincodehub.jpa.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author YangJian
 * @version 1.0.0
 * @title Test
 * @create 2025/5/12 8:36
 * @description <TODO description class purpose>
 */
@SpringBootTest
public class Test {

    @Autowired
    IdGenerateService idGenerateService;

    @Autowired
    LoginUserService loginUserService;

    @org.junit.jupiter.api.Test
    public void test1(){
        long l = idGenerateService.generateUserId();
        System.out.println("l===="+l);
    }
    @org.junit.jupiter.api.Test
    public void test2(){
        LoginUser  loginUser = new LoginUser(null,"a3","","cc123");
       loginUserService.findAll(loginUser);
    }

}

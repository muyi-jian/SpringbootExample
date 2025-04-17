package com.yj.springboot3web.controller;


import com.yj.springboot3web.entity.LoginUser;
import com.yj.springboot3web.entity.User;
import com.yj.springboot3web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserController
 * @create 2025/4/16 8:17
 * @description <TODO description class purpose>
 */
@RestController
public class UserController {
    @Autowired
    private  MyProperties myProperties;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String operateUser()  {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new LoginUser("a1",  "123456", "aa123"));
        userRepository.save(new LoginUser("a2", "123456", "bb123"));
        userRepository.save(new LoginUser("a3", "123456", "cc123"));

        userRepository.delete(userRepository.findUserByUsername("a1"));
        return "ok";
    }


    @GetMapping("/user")
    public User getUser(){
        User user = new User("yj", 18);
        System.out.println(myProperties.getTitle());
        return user;
    }
}

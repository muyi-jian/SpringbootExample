package com.yj.rbacsecurity.controller;

import com.yj.rbacsecurity.entity.User;
import com.yj.rbacsecurity.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/toLogin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    UserMapper userMapper;

    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/user/save")
    public String save(String username,String password){

        //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user.setEnabled(true);

        userMapper.insert(user);

        return "redirect:/login";
    }
}

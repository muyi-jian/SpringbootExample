package com.spring.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @PostMapping("/failre")
    public String failre(){
        return "failre";
    }

    @GetMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/user")
    public String user(){
        return "user";
    }

}

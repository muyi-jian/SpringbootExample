package com.yj.springboot3.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *@title HelloController
 *@description <TODO description class purpose>
 *@author YangJian
 *@version 1.0.0
 *@create 2025/1/26 21:41
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}

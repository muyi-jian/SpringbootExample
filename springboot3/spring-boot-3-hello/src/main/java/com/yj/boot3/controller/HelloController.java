package com.yj.boot3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @date 2023/11/25 18:31
 * @description
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Spring Boot3.x！！！";
    }
}

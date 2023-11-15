package com.yj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @date 2023/11/15 16:57
 * @description
 */
@RestController
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello Spring Boot 3.X!!!";
    }
}

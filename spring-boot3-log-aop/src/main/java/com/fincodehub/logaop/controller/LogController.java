package com.fincodehub.logaop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LogController
 * @create 2025/4/24 22:37
 * @description <TODO description class purpose>
 */
@RestController
public class LogController {
    @GetMapping("/test")
    public String testLog(){
        return "test-log-AOP";
    }
}

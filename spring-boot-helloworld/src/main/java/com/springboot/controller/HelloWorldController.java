package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/11/21 21:48
 */
@RestController
public class HelloWorldController {

    @GetMapping("helloWorld")
    public String helloWorld(){
        return "HelloWorld";
    }
}

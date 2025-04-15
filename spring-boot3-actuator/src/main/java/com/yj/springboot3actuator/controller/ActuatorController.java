package com.yj.springboot3actuator.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ActuatorController
 * @create 2025/4/14 20:43
 * @description <TODO description class purpose>
 */
@Controller
public class ActuatorController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

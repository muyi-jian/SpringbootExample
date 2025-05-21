package com.fincodehub.shiro.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YangJian
 * @version 1.0.0
 * @title HelloController
 * @create 2025/5/18 11:52
 * @description <TODO description class purpose>
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Shiro");
        return "index";
    }
}

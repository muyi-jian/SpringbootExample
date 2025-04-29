package com.fincodehub.thymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YangJian
 * @version 1.0.0
 * @title TestController
 * @create 2025/4/29 14:33
 * @description <TODO description class purpose>
 */
@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        // 重定向
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String find(Model model) {
        //向请求作用域中写入数据
        model.addAttribute("name", "Hello Thymeleaf");
        //返回视图
        return "index";
    }
}


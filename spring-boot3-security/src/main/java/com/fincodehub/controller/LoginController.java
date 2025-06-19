package com.fincodehub.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginController
 * @create 2025/6/10 21:11
 * @description <TODO description class purpose>
 */
@Controller
@Slf4j
public class LoginController {



    @GetMapping("/login")
    public String login() {
        return "login";  // 返回名为 "login" 的模板或 HTML 页面
    }


    @GetMapping("/index")
    public String home() {
        log.info("index");
        return "index";  // 返回名为 "index" 的模板或 HTML 页面
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        log.info("admin");
        return "欢迎进入管理页面";
    }
}

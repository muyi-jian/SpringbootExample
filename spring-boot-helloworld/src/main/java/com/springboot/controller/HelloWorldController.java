package com.springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/detailByParam")
    public void detailByParam(@RequestParam Map<String, String> params) {
        System.out.println(">>>id="+params.get("id")+",roleName="+params.get("roleName")+",roleDes="+params.get("roleDes"));
    }

    @PostMapping("/efly/get")
    @ResponseBody
    public String getEFlyReturnPR(@RequestParam Map<String, String> params) {
        System.out.println("接收到的x-www-form-urlencoded");
        System.out.println(params);
        return "接收成功";
    }
}



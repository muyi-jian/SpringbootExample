package com.fincodehub.logaop.controller;


import com.fincodehub.logaop.log.IPBlackList;
import com.fincodehub.logaop.log.MyLog;
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
public class MyLogController {
    @GetMapping("/log")
    @MyLog
    public String testLog(){
        return "test-mylog-AOP";
    }

    @GetMapping("/ip")
    @IPBlackList
    public String testip(){
        return "test-myip-AOP";
    }
}

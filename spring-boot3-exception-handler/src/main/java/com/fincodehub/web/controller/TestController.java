package com.fincodehub.web.controller;


import com.fincodehub.web.entity.Login;
import com.fincodehub.web.entity.User;
import com.fincodehub.web.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author YangJian
 * @version 1.0.0
 * @title TestController
 * @create 2025/4/28 21:06
 * @description <TODO description class purpose>
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @PostMapping("/user")
    public ResponseResult<User> getUser(@RequestBody User user) {
        log.info("获取User, User:{}", user);

        return ResponseResult.success(user);
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Login login) {
        log.info("登录, User:{}", login);

        return ResponseResult.success("登录成功");
    }
}

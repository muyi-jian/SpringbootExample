package com.yj.springboot.controller;

import com.yj.springboot.util.PhoneCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/verifyCode")
    public String testRedis() {
        String phone = "15269173997";
        String verifyCode = PhoneCodeUtils.verifyCode(phone, redisTemplate);
        return verifyCode;
    }

    @GetMapping("/getCode/{code}")
    public String testRedis(@PathVariable("code") String code) {
        String phone = "15269173997";
        String verifyRt = PhoneCodeUtils.getRedisCode(phone,code, redisTemplate);
        return verifyRt;
    }
}

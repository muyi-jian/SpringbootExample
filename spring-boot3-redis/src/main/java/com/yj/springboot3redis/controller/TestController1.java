package com.yj.springboot3redis.controller;


import com.yj.springboot3redis.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title TestController
 * @create 2025/4/17 8:12
 * @description <TODO description class purpose>
 */
@Slf4j
@RestController
public class TestController1 {


    @Resource
    @Qualifier("objectRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate;

    @GetMapping(value="/setObject")
    public String setObject() {
        String key = "obj";
        User user = new User(1,"tom",22,"1234567@qq.com");
        redisTemplate.opsForValue().set(key,user);
        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
        return "ok";
    }



}

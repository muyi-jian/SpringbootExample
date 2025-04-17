package com.yj.springboot3redis.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class TestController {

    @Autowired
    @Qualifier("stringRedisTemplate")
    public RedisTemplate<String, String> redisTemplate;



    @GetMapping(value="/setString")
    public String setString() {
        String key = "test";
        // 插入一个字符串
        redisTemplate.opsForValue().set(key,"123");
        // 再从redis读取这个key值对应的value
        String s = redisTemplate.opsForValue().get(key);
        System.out.println(s);
        // 再次设置值，会进行覆盖
        // redisTemplate.opsForValue().set(key,"456");
        // s = redisTemplate.opsForValue().get(key);
        return s;
    }

    @GetMapping(value="/getV/{key}")
    public String getV(@PathVariable String key){
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }


    @GetMapping(value="/append")
    public void append() {
        redisTemplate.opsForValue().set("tom", "hello");
        String str = redisTemplate.opsForValue().get("tom");
        log.info("str:{}", str);
        // 追加字符串
        redisTemplate.opsForValue().append("tom", "哈哈");
        String appendStr = redisTemplate.opsForValue().get("tom");
        log.info("appendStr:{}", appendStr);
        // 获取长度
        Long size = redisTemplate.opsForValue().size("tom");
        log.info("str-size:{}", size);
    }


}

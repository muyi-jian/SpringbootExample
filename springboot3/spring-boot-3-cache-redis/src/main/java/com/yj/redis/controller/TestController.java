package com.yj.redis.controller;

import com.yj.redis.entity.User;
import com.yj.redis.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/20 9:40
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    public UserService userService;
    @Resource
    public StringRedisTemplate stringRedisTemplate;
    @Resource
    public RedisTemplate redisTemplate;

    @GetMapping("/set/{key}/{value}")
    public String setRedis(@PathVariable("key") String key, @PathVariable("value") String value){
       if (StringUtils.isBlank(key) || StringUtils.isBlank(value)){
           return "key 或 value 不能为空";
       }
       stringRedisTemplate.opsForValue().set(key, value);
        String kv = stringRedisTemplate.opsForValue().get(key);
        log.debug("【{}】= {}", key,kv);
       return "从redis中获取到"+key+"的值："+kv;
    }

    @GetMapping("/pool")
    public String testRedisPool(){
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        String kv = stringRedisTemplate.opsForValue().get("count");
        return "从redis中获取到count的值："+kv;
    }
    @PostMapping("/saveOrUpdate")
    public User saveOrUpdate(@RequestBody User user){
        User user1 = userService.saveOrUpdate(user);

        return user1;
    }
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id){
        User user1 = userService.get(id);

        return user1;
    }
    @GetMapping("/del/{id}")
    public String delUser(@PathVariable Long id){
        userService.delete(id);

        return "ok";
    }




}

package com.yj.ehcache.controller;


import com.yj.ehcache.entity.User;
import com.yj.ehcache.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

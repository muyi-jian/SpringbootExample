package com.fincodehub.jpa.controller;


import com.fincodehub.jpa.entity.User;
import com.fincodehub.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserController
 * @create 2025/5/8 21:27
 * @description <TODO description class purpose>
 */


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users/find/{username}")
    public List<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/users/email/{keyword}")
    public List<User> findByEmailLike(@PathVariable String keyword) {
        return userService.findByEmailLike(keyword);
    }

    @PostMapping("/users/u")
    public void updateYourEntity(@RequestBody User user) throws InterruptedException {
        User entity = userService.findById(user.getId());
        System.out.println(entity.toString());
        Thread.sleep(9000);
        entity.setUsername(user.getUsername());

        userService.save(entity); // 这里会触发乐观锁检查  保存实体，JPA会检查版本号
    }
}

package com.fincodehub.controller;


import com.fincodehub.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangJian
 * @version 1.0.0
 * @title dpringdoc
 * @create 2025/5/21 20:12
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理", description = "管理用户信息")
public class Knife4jController {

    static Map<String, User> map = new HashMap<>();
    static {
        map.put("1", new User("FCH", 35));
        map.put("2", new User("Tom", 22));
    }
    @PostMapping("/add")
    @Operation(summary = "创建用户", description = "创建一个新的用户")
    public User hello(@RequestBody User user){
        map.put("3", user);
        System.out.println(user);
        return user;
    }
    @Operation(summary = "获取用户信息", description = "根据 ID 获取用户信息")
    @GetMapping("/{id}")
    public User getUsrById(@Parameter(description = "用户 ID") @PathVariable String id) {
        return map.get(id);
    }
}

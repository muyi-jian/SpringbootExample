package com.yj.springboot3actuator.controller;


/**
 * @author YangJian
 * @version 1.0.0
 * @title ApiController
 * @create 2025/6/20 10:35
 * @description <TODO description class purpose>
 */

import com.yj.springboot3actuator.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/{id}")
    public ResponseEntity<User> query(@PathVariable Long id) {
        System.out.println("id: " + id);
        return ResponseEntity.ok(new User(id, "姓名 - " + id)) ;
    }
    @GetMapping("")
    public ResponseEntity<List<User>> list(String name) {
    return ResponseEntity.ok(
            List.of(new User(1L, name + " - 1"), new User(2L, name + " - 2"))) ;
    }


}

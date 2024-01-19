package com.yj.jdbctemplate.controller;

import com.yj.jdbctemplate.entity.User;
import com.yj.jdbctemplate.result.ResponseObject;
import com.yj.jdbctemplate.result.StatusEnum;
import com.yj.jdbctemplate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/18 20:47
 */
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 构造器注入
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseObject<User> insertUser(@RequestBody User user) {
        log.info("插入前：{}",user);
        int num = userService.insertUser(user);
        log.info("插入后：{}",user);

        if (num > 0){
           return ResponseObject.success(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage(),user);
        }else{
            return ResponseObject.fail(StatusEnum.ERROR.getCode(), StatusEnum.ERROR.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseObject<User> updateUser(@RequestBody User user) {
        log.info("更新前：{}",user);
        int num = userService.updateUser(user);
        log.info("更新后：{}",user);

        if (num > 0){
            return ResponseObject.success(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage(),user);
        }else{
            return ResponseObject.fail(StatusEnum.ERROR.getCode(), StatusEnum.ERROR.getMessage());
        }
    }
    @DeleteMapping("/user/{id}")
    public ResponseObject deleteUser(@PathVariable Long id) {
        log.info("删除id：{}",id);
        int num = userService.deleteUser(id);
        if (num > 0){
            return ResponseObject.success(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage());
        }else{
            return ResponseObject.fail(StatusEnum.ERROR.getCode(), StatusEnum.ERROR.getMessage());
        }
    }


    @GetMapping("/user")
    public ResponseObject<List<User>> getUser(User user) {
        List<User> userList = userService.getAll();
        return ResponseObject.success(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage(),userList);
    }

}

package com.yj.springboot3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.springboot3.entity.User;
import com.yj.springboot3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YangJian
 * @date 2024/1/1 11:27
 * @description
 */
@RestController
public class UserController {

    @Autowired
    public UserService userService;


    @GetMapping("/getUserList")
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();

        System.out.println(userList);
        return userList;
    }

    // 方便测试使用get请求
    @GetMapping("/addUser")
    public String addUser(){

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(20+i);
            user.setName("tom"+i);
            user.setEmail("tom"+i+"@qq.com");
            userService.addUser(user);
        }

        return "添加成功";
    }

    @GetMapping("/getUserByPage/{pageNo}/{pageSize}")
    public IPage<User>  getUserByPage(@PathVariable("pageNo") Integer pageNo,
                                @PathVariable("pageSize") Integer pageSize,
                                String name){

        //分页信息封装Page对象
        Page<User> pageParam =new Page<>(pageNo,pageSize);

        return userService.getUserByPage(pageParam, name);
    }
}

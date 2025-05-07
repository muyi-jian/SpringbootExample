package com.fincodehub.jdbctemplate.controller;


import com.fincodehub.jdbctemplate.entity.LoginUser;
import com.fincodehub.jdbctemplate.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title JdbcController
 * @create 2025/5/7 21:44
 * @description <TODO description class purpose>
 */
@RestController
public class JdbcController {

    @Autowired
    private LoginUserService  loginUserService;

    @GetMapping("test")
    public List<LoginUser> test(){
        return loginUserService.queryLoginUser();
    }

    @PostMapping("save")
    public List<LoginUser>  save(@RequestBody LoginUser loginUser){
        System.out.println("-------------------"+loginUser.toString());
        loginUserService.save(loginUser);
        return loginUserService.queryLoginUser();
    }
    @PostMapping("update")
    public List<LoginUser>  update(@RequestBody LoginUser loginUser){
        loginUserService.update(loginUser);
        return loginUserService.queryLoginUser();
    }

    @GetMapping("deleteById/{id}")
    public List<LoginUser>  deleteById(@PathVariable Integer id){
        loginUserService.deleteById(id);
        return loginUserService.queryLoginUser();
    }

}

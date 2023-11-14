package com.yj.result.controller;

import com.yj.result.annotaion.IgnoreResponseHandler;
import com.yj.result.entity.User;
import com.yj.result.exception.MyException;
import com.yj.result.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public ResultUtil<User> sayHello(){
        ResultUtil<User> rt = new ResultUtil<User>();
        User user = new User();
        user.setName("yy");
        user.setAddress("北京");
        user.setAge(34);
        return rt.success(2000,"成功",user);
    }

    //忽略特殊返回数据格式：特殊格式：{"data":"33"} 添加注解后：33
    @IgnoreResponseHandler
    @GetMapping("/test")
    public String test(){
        return "33";
    }
    @GetMapping("/fail")
    public ResultUtil<User> fail(){
        try {
            ResultUtil<User> rt = new ResultUtil<User>();
            User user = new User();
            user.setName("yy");
            user.setAddress("北京");
            user.setAge(34);
            int p = 10/0;
            return rt.success(2000,"成功",user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户失败", e);
        }
    }
    @GetMapping("/fail1")
    public ResultUtil<User> fail1(){
        try {
            ResultUtil<User> rt = new ResultUtil<User>();
            User user = new User();
            user.setName("yy");
            user.setAddress("北京");
            user.setAge(34);
            int p = 10/0;
            return rt.success(2000,"成功",user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("测试MyException");
        }
    }

}

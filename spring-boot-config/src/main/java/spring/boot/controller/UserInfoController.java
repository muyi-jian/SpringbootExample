package spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.entity.UserInfo;
import spring.boot.entity.UserInfo1;
import spring.boot.entity.UserInfo2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjian
 * @date 2022/11/22 18:36
 */
@RestController
public class UserInfoController {
    @Autowired
    UserInfo userInfo;

    @Autowired
    UserInfo1 userInfo1;

    @Autowired
    UserInfo2 userInfo2;

    @Autowired
    private Environment env;

    @GetMapping("/user/userInfo")
    public UserInfo getUserInfo(){
        return userInfo;
    }

    @GetMapping("/user/userInfo1")
    public UserInfo1 getUserInfo1(){
        return userInfo1;
    }


    @GetMapping("/user/userInfo2")
    public UserInfo2 getUserInfo2(){
        return userInfo2;
    }

    @GetMapping("/user/userInfo3")
    public  Map<String, Object>  getUserInfo3(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", env.getProperty("userinfo.name"));
        map.put("address", env.getProperty("userinfo.address"));
        map.put("age", env.getProperty("userinfo.age"));
        return map;
    }
}

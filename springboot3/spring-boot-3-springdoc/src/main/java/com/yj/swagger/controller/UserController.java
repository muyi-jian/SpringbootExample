package com.yj.swagger.controller;

import com.yj.swagger.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @date 2023/12/31 10:31
 * @description
 */
@RestController()
@RequestMapping("/user")

@Tag(name = "用户接口")
public class UserController {


    @GetMapping("/getUserById/{id}")
    @Parameter( description = "根据id获取用户信息")
    public User getUserById(@Parameter( description = "用户id")@PathVariable("id") Integer id) {
        return new User(id, "张三", 18, "北京");
    }
}

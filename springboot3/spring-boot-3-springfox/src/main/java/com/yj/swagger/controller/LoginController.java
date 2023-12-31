package com.yj.swagger.controller;

import com.yj.swagger.entity.Login;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @date 2023/12/31 10:31
 * @description
 */
@RestController()
@RequestMapping("/system")
@Tag(name = "登录接口")
public class LoginController {

    // 登录代码
    @PostMapping("/login")
    @Parameter(description = "用户登录入口")
    public String login(@Parameter( description = "用户登录信息")@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        System.out.println("username:" + username + " password:" + password);
        if ("admin".equals(username) && "123456".equals(password)) {
            return "登录成功";
        }
        return "登录失败";
    }
}

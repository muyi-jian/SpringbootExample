package com.fincodehub.shiro.controller;


/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginController
 * @create 2025/5/24 12:37
 * @description <TODO description class purpose>
 */

import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/userLogin")
    public String login(String name, String pwd, @RequestParam(defaultValue = "false")boolean rememberMe, HttpSession session) {
        System.out.println("name = " + name);
        System.out.println("pwd = " + pwd);
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 封装请求数据到token对象中
        AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
        // 调用login方法进行登录认证
        try {
            subject.login(token);
            System.out.println("登录成功");
            session.setAttribute("user", name);
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }
    @GetMapping("/userLoginRm")
    public String userLogin(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "main";
    }
    @GetMapping("/login")
    public String toLogin() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        System.out.println("退出登录");
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
    @RequiresRoles("admin")
    @GetMapping("/userLoginRoles")
    @ResponseBody
    public String userLoginRoles() {
        Session session=SecurityUtils.getSubject().getSession();
        System.out.println("session = " + session.getAttributeKeys());
        // 遍历会话中的所有键和值
        for (Object key : session.getAttributeKeys()) {
            Object value = session.getAttribute(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }

    @RequiresPermissions("user:delete")
    @GetMapping("/userPermissions")
    @ResponseBody
    public String userPermissions() {
        System.out.println("登录认证验证权限");
        return "验证权限成功";
    }
}

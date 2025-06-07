package com.fincodehub.controller;


import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SessionController
 * @create 2025/6/7 8:30
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/set")
    public String setSession(HttpSession session) {
        session.setAttribute("user", "fincodehub");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", "fincodehub");
        jsonObject.put("userId", "fch");
        jsonObject.put("age", "12");
        session.setAttribute("fch", jsonObject);
        return "Session set for user: fincodehub";
    }

    @GetMapping("/get")
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("user"));
        System.out.println(session.getAttribute("fch"));

        return "User from session: " + session.getAttribute("user");
    }
}

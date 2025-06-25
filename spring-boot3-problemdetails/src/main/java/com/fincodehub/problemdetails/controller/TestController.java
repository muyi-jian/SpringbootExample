package com.fincodehub.problemdetails.controller;


import com.alibaba.fastjson.JSONObject;
import com.fincodehub.problemdetails.exception.DivideByZeroException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title TestController
 * @create 2025/6/25 21:39
 * @description <TODO description class purpose>
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public JSONObject test() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw  new DivideByZeroException("除数不能为0");
        }

        // 返回一个json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("message", "success");
        return jsonObject;
    }
}

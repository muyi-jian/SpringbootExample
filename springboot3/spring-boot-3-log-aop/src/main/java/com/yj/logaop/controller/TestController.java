package com.yj.logaop.controller;


import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 测试 Controller
 * </p>
 *
 */
@Slf4j
@RestController
public class TestController {

    /**
     * 测试方法
     *
     * @param who 测试参数
     * @return String
     */
    @GetMapping("/test")
    public String test(String who) {

        return StringUtils.isBlank(who) ? "me" : who;
    }

    /**
     *  测试post json方法
     * @param map 请求的json参数
     * @return JSON
     */
    @PostMapping("/testJson")
    public Map<String, Object> testJson(@RequestBody Map<String, Object> map) {


        log.info(map.toString());
        return map;
    }
}

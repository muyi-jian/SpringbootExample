package com.funcodehub.log.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LogController
 * @create 2025/4/24 19:54
 * @description <TODO description class purpose>
 */
@RestController
@Slf4j
public class LogController {

    @GetMapping("/LOG")
    public String testLog() {
        log.trace("test log trace");
        log.debug("test log debug");
        log.info("test log info");
        log.warn("test log warn");
       log.error("test log error");
        return "test log";
    }
}

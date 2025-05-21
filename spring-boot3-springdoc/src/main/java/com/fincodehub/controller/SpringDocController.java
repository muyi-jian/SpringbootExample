package com.fincodehub.controller;


import com.fincodehub.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title dpringdoc
 * @create 2025/5/21 20:12
 * @description <TODO description class purpose>
 */
@RestController
public class SpringDocController {

    @GetMapping("/test/{n}")
    public String hello(@PathVariable("n") String n){
       if ("1".equals(n)){
           throw new ServiceException(500,"test");
       }
        return "hello";
    }
}

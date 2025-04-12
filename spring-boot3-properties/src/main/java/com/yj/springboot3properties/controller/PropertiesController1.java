package com.yj.springboot3properties.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YangJian
 * @version 1.0.0
 * @title PropertiesController
 * @create 2025/4/11 13:32
 * @description 测试读取properties
 */
@RestController
public class PropertiesController1 {

    @Value("${yj.name}")
    private String name;

    @GetMapping("/rp")
    public String readProperties() {
        String value = "";
        Properties props = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/config/db.properties")) {
            props.load(input);
           props.forEach((k,v)->{
               System.out.println(k+"--"+v);
           });
            value = props.getProperty("db.username") + ":" + props.getProperty("db.password");
        }catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }

    @GetMapping("/rp1")
    public String rp1() {

        return name;
    }

}

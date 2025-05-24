package com.fincodehub.shiro;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ShiroApplication
 * @create 2025/5/18 11:53
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.shiro.mapper")
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

}

package com.fincodehub.mybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MybatisPlusApplication
 * @create 2025/4/29 16:38
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.mybatis.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}

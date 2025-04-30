package com.fincodehub.quarz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author YangJian
 * @version 1.0.0
 * @title quartz
 * @create 2025/4/30 11:14
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@EnableScheduling
public class QuarzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuarzApplication.class,args);
    }
}

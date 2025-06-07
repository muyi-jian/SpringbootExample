package com.fincodehub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SessionRedisApplication
 * @create 2025/6/7 8:27
 * @description <TODO description class purpose>
 */
@SpringBootApplication
// @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60, redisNamespace = "fincodehub:session")
public class SessionRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionRedisApplication.class, args);
    }
}

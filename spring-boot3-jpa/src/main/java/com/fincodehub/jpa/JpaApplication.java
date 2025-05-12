package com.fincodehub.jpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author YangJian
 * @version 1.0.0
 * @title JpaApplication
 * @create 2025/5/8 16:39
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@EnableJpaAuditing
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}

package com.fincodehub.task;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author YangJian
 * @version 1.0.0
 * @title task
 * @create 2025/5/17 22:12
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@EnableScheduling //表示开启定时任务
public  class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
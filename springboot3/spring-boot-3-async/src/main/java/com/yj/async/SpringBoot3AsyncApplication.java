package com.yj.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringBoot3AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3AsyncApplication.class, args);
    }

}

package com.yj.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.yj.jpa.repository")
public class SpringBoot3JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3JpaApplication.class, args);
    }

}

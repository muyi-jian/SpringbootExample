package com.yj.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBoot3CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3CacheApplication.class, args);
    }

}

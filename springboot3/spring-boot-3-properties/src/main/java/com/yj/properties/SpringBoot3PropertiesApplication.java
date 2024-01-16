package com.yj.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"com.yj.properties.entity"})
public class SpringBoot3PropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3PropertiesApplication.class, args);
    }

}

package com.yj.springboot3properties.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ConfigBeanValue1
 * @create 2025/4/12 10:32
 * @description <TODO description class purpose>
 */
@Configuration
public class ConfigBeanValue4 {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("/config/app.yml"));
        configurer.setFileEncoding("UTF-8");
        return configurer;
    }

}

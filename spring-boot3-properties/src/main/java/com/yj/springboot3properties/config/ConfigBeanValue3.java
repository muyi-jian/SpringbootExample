package com.yj.springboot3properties.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ConfigBeanValue1
 * @create 2025/4/12 10:32
 * @description <TODO description class purpose>
 */
@Configuration
@PropertySource("classpath:config/db.properties")
public class ConfigBeanValue3 {
    @Value("${db.username}")
    private String name;
    @Value("${db.password}")
    private String password;

    public String printConfig(){
        return "get properties by ''@Value''："+
                //使用@Value注解读取
                " name=" + name+
                " password=" + password;
    }
}

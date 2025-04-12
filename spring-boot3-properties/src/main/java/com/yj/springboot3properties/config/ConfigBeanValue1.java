package com.yj.springboot3properties.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ConfigBeanValue1
 * @create 2025/4/12 10:32
 * @description <TODO description class purpose>
 */
@Data
@Component
@ConfigurationProperties(prefix = "fin-code-hub")
// @PropertySource("classpath:config/app.yml")
@PropertySource("application.yml")
public class ConfigBeanValue1 {
    private String name;
    private String site;
    private String author;
    private List<String> users;
    private Map<String,String> params;
    private Map<String,String> security;
}

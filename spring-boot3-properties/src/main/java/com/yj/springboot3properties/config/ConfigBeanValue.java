package com.yj.springboot3properties.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ConfigBeanValue
 * @create 2025/4/12 9:48
 * @description <TODO description class purpose>
 */
@Component
public class ConfigBeanValue {
    @Value("${finCodeHub.name}")
    private String name;
    @Value("${finCodeHub.site}")
    private String site;

    public String printConfig(){
        return "get properties by ''@Value''："+
        //使用@Value注解读取
        " name=" + name+
        " site=" + site;
    }
}

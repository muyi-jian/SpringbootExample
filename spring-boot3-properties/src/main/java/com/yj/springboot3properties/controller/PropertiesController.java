package com.yj.springboot3properties.controller;


import com.yj.springboot3properties.config.ConfigBeanValue;
import com.yj.springboot3properties.config.ConfigBeanValue1;
import com.yj.springboot3properties.config.ConfigBeanValue3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title PropertiesController
 * @create 2025/4/11 13:32
 * @description 测试读取properties
 */
@RestController
public class PropertiesController {

    @Value("${finCodeHub.name}")
    private String name;

    @Autowired
    private ConfigBeanValue configBeanValue;

    @Autowired
    private ConfigBeanValue1 configBeanValue1;

    @Autowired
    public Environment environment;

    @Autowired
    private ConfigBeanValue3 configBeanValue3;

    @GetMapping("/name")
    public String getName() {
        return name;
    }
    @GetMapping("/configBeanValue")
    public String getConfigBeanValue() {
        return  configBeanValue.printConfig();
    }
    @GetMapping("/configBeanValue1")
    public ConfigBeanValue1 getConfigBeanValue1() {
        return  configBeanValue1;
    }

    @GetMapping("/getEnv")
    public String getEnv() {
        return "get properties by ''@Value''："+
                //使用@Value注解读取
                " name=" + environment.getProperty("finCodeHub.name")+
                " site=" + environment.getProperty("finCodeHub.site");
    }
    @GetMapping("/configBeanValue3")
    public String configBeanValue3() {
        return configBeanValue3.printConfig();
    }

}

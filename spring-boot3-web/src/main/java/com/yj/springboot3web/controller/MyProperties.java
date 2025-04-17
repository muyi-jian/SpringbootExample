package com.yj.springboot3web.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyProperties
 * @create 2025/4/16 8:39
 * @description <TODO description class purpose>
 */
@Component
public class MyProperties {
    @Value("${com.yj.title}")
    public String title;
    @Value("${com.yj.description}")
    public String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

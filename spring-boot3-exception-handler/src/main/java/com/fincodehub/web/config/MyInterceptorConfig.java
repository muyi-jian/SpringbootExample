package com.fincodehub.web.config;


import com.fincodehub.web.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册配置拦截器：实现WebMvcConfigurer接⼝，并重写addInterceptors⽅法
 * @author YangJian
 * @version 1.0.0
 * @title MyInterceptorConfig
 * @create 2025/4/28 20:53
 * @description <TODO description class purpose>
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private  MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // 自定义拦截器
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**") // 拦截路径，设置拦截器拦截的请求路径，/** 表⽰拦截所有请求
                .excludePathPatterns();//排除拦截路径
    }
}

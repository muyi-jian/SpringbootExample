package com.fincodehub.web.config;


import com.fincodehub.web.filter.MyFilter1;
import com.fincodehub.web.filter.MyFilter2;
import com.fincodehub.web.filter.MyFilter3;
import com.fincodehub.web.filter.MyOncePerRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangJian
 * @version 1.0.0
 * @title FilterConfig
 * @create 2025/4/28 23:37
 * @description <TODO description class purpose>
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> myFilter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter1());
        bean.addUrlPatterns("/*"); // 拦截所有请求
        bean.setOrder(1); // 设置过滤器顺序，数值越小优先级越高
        return bean;
    }
    @Bean
    public FilterRegistrationBean<MyFilter2> myFilter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }
    @Bean
    public FilterRegistrationBean<MyFilter3> MyFilter3() {
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter3());
        bean.addUrlPatterns("/*");
        bean.setOrder(3);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyOncePerRequestFilter> myFilterRegistration() {
        FilterRegistrationBean<MyOncePerRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MyOncePerRequestFilter());
        registration.addUrlPatterns("/*"); // 拦截所有请求
        registration.setName("myOncePerRequestFilter");
        registration.setOrder(4); // 设置过滤器顺序
        return registration;
    }
}


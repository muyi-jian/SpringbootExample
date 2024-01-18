package com.yj.beetl;

import org.beetl.ext.spring6.EnableBeetl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBeetl //会拦截.btl文件使用Beetl语法解析
public class SpringBoot3TemplateBeetlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3TemplateBeetlApplication.class, args);
    }

}

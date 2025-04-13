package com.yj.springboot3web;

import com.yj.springboot3web.config.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot3WebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBoot3WebApplication.class);
        //添加自定义banner
        springApplication.setBanner(new MyBanner());
        springApplication.run(args);
    }

}

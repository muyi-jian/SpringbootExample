package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloworldApplication.class, args);
        //获取入口SpringBoot类
        /*SpringApplication springApplication = new SpringApplication(SpringBootHelloworldApplication.class);
        //设置它的属性
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);*/
    }

}

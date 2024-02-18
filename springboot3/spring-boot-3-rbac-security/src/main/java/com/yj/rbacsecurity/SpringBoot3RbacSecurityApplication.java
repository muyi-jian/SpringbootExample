package com.yj.rbacsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * security登录默认用户名:user ，不配置会随机生成一个密码
 */
@SpringBootApplication
public class SpringBoot3RbacSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3RbacSecurityApplication.class, args);
    }

}

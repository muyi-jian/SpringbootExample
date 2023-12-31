package com.yj.springboot3.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangJian
 * @date 2023/11/28 21:53
 * @description
 */
@Configuration
@MapperScan("com.yj.springboot3")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}

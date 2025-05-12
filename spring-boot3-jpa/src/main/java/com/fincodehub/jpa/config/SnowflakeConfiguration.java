package com.fincodehub.jpa.config;


import com.fincodehub.jpa.util.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SnowflakeConfiguration
 * @create 2025/5/12 8:33
 * @description <TODO description class purpose>
 */
@Configuration
public class SnowflakeConfiguration {
    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        // 假设机器ID为 1（可以根据实际部署环境配置）
        return new SnowflakeIdGenerator(1);
    }
}

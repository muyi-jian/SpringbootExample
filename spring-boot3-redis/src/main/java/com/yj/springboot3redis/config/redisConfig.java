package com.yj.springboot3redis.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author YangJian
 * @version 1.0.0
 * @title redisConfig
 * @create 2025/4/16 21:45
 * @description <TODO description class purpose>
 */
@Configuration
public class redisConfig {

    //仅在当前 Spring 容器中不存在名为 redisTemplate 的 Bean 时，才会创建被标注的 Bean
    // @ConditionalOnMissingBean(name = "redisTemplate")
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 默认为utf-8,可以进行修改
        template.setKeySerializer(new StringRedisSerializer());
        // 原版默认使用jdk的序列化方式JdkSerializationRedisSerializer
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setValueSerializer(serializer);
        // 设置Hash的序列化化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        // 设置属性
        template.afterPropertiesSet();
        return template;
    }
}

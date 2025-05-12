package com.fincodehub.jpa.config;


/**
 * @title JpaConfig
 * @author YangJian
 * @version 1.0.0
 * @create 2025/5/9 9:25
 * @description <TODO description class purpose>
 */

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JpaConfig {

    // @Bean(name = "myAuditorAware")
    // @ConditionalOnMissingBean(name = "myAuditorAware")
    // MyAuditorAware myAuditorAware() {
    //     return new MyAuditorAware();
    // }
   @Bean(name = "myAuditorAware")
    @ConditionalOnMissingBean(name = "myAuditorAware")
   CustomAuditor myAuditorAware() {
        return new CustomAuditor();
    }


}
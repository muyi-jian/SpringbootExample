/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 22:30
 * @version 1.0.0
 */
package com.yj.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 22:30
 * @version 1.0.0
 */
@Configuration
public class AdminMonitorSecurityConfig {
    /**
     * 增加 springboot 服务端安全验证 解决客户端注册报401
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll());
         //跨域漏洞防御：关闭
         http.csrf(Customizer.withDefaults());
        return http.build();
    }
}

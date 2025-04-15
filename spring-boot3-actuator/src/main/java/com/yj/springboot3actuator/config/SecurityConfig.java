package com.yj.springboot3actuator.config;


import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SecurityConfig
 * @create 2025/4/14 21:17
 * @description <TODO description class purpose>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/").permitAll()
                            // 保护所有 Actuator 端点（需 ENDPOINT_ADMIN 角色）
                            .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ENDPOINT_ADMIN")
                            // 其他非端点请求可在此继续配置
                            .anyRequest().authenticated();
                })
                //                .csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers(EndpointRequest.toAnyEndpoint()))
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutUrl("/logout"))
                .build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("test").password("{noop}test")
                .roles("ENDPOINT_ADMIN", "ADMIN", "TEST").build());
        manager.createUser(User.withUsername("root").password("{noop}root")
                .roles("ADMIN").build());
        return manager;
    }
}
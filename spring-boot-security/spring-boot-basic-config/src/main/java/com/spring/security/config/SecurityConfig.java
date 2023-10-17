package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorizeHttpRequests: 针对http请求进行授权配置
        // login登录页面需要匿名访问
        // permitAll:具有所有权限，也就可以匿名访问
        // anyRequest: 任何请求 所有请求
        // authenticated: 认证【登录】
        http.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated());

        // loginPage:登录页面
        // loginProcessingUrl: 登录接口 过滤器
        // defaultSuccessUrl: 登录成功后访问的页面
        http.formLogin(formLogin->
                formLogin.loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index")
        );

        http.csrf(Customizer.withDefaults());//跨域漏洞防御：关闭
        //http.csrf(e->e.disable());
        //http.csrf(csrf->csrf.disable());//相当于http.csrf(Customizer.withDefaults());
        // 退出
        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
    }

}

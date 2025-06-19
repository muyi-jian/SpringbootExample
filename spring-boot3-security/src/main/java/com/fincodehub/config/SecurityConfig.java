package com.fincodehub.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityFilterChain 定义了所有 HTTP 请求的安全策略。在这里，/ 路径对所有人公开，而其他路径需要用户身份认证。
 * UserDetailsService 提供了用户的详细信息，包括用户名、密码及角色。在这个例子中，我们创建了一个用户名为 "user" 的用户，密码为 "password"（经过加密处理），并分配了 "USER" 角色，如果不配置，系统则会在日志中输出名为 user 的用户对应的密码：Using generated security password: b9fe7857-97a3-4db7-9602-9e10db56496d。
 * PasswordEncoder 通过 BCryptPasswordEncoder 实现密码加密，以确保用户密码存储时是安全的。
 * `@EnableWebSecurity注解启动 `Spring Security 的自动配置，使得应用能够自动集成 Spring Security 提供的安全功能。
 * @author YangJian
 * @version 1.0.0
 * @title SecurityConfig
 * @create 2025/6/10 20:43
 * @description <TODO description class purpose>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())  // 若某些请求无需 CSRF 保护可以禁用
                .authorizeHttpRequests(auth->
                auth.requestMatchers("/").permitAll()   // 公开访问
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // 只有 ADMIN 角色可以访问 /admin 目录下的资源
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // USER 和 ADMIN 角色都可以访问 /user 目录下的资源
                        .anyRequest().authenticated()    // 其他接口需认证
        )
                // .formLogin(Customizer.withDefaults())   // 使用默认登录
                // .httpBasic(Customizer.withDefaults());  // 使用Http Basic 认证
                .formLogin(form -> form
                        .loginPage("/login")    //  指定登录页面
                        .permitAll()    // 登录页面无需认证
                )
                // .logout(logout -> logout.permitAll());   //  允许登出
                .logout(logout -> logout.logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                ).httpBasic(Customizer.withDefaults());  // 使用 HTTP Basic 认证
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        //  创建用户
        UserDetails user = User.builder()
                .username("fincodehub")
                .password(passwordEncoder.encode("fch123456"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        // return new InMemoryUserDetailsManager(user);
        userDetailsService.createUser(user);
        userDetailsService.createUser(admin);
        return userDetailsService;
    }
    @Bean
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder(); //使用BCrypt 进行加密
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("123456");
        System.out.println(encoder.matches("123456", result));
    }
}

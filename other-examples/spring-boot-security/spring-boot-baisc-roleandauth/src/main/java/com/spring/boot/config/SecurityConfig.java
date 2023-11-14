package com.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeHttpRequests->
                authorizeHttpRequests
                        // 角色
                        //.requestMatchers("/admin/api").hasRole("admin")
                        //.requestMatchers("/user/api").hasAnyRole("admin","user")
                        .requestMatchers("/admin/api").hasAuthority("admin:api")
                        .requestMatchers("/user/api").hasAnyAuthority("admin:api","user:api")
                        .requestMatchers("/app/api").permitAll()
                        .requestMatchers("/test/?").hasAnyAuthority("admin:api")
                        .requestMatchers("/test/api/*/*").hasAnyAuthority("admin:api")
                        .requestMatchers("/test/a/**").hasAnyAuthority("admin:api")
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin(formLogin->
                formLogin.loginPage("/login")
                        .loginProcessingUrl("/login").permitAll()
                        .defaultSuccessUrl("/index")
        );

        http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));
        http.csrf(csrf->csrf.disable());

        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        // 角色
        //UserDetails user1 = User.withUsername("admin").password("123").roles("admin", "user").build();
        //UserDetails user2 = User.withUsername("user").password("123").roles("user").build();
        // 权限
        UserDetails user1 = User.withUsername("admin").password("123").authorities("admin:api", "user:api").build();
        UserDetails user2 = User.withUsername("user").password("123").authorities("user:api").build();



        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 这里使用 NoOpPasswordEncode，即不对密码进行加密。该方法因安全性不足够已被标记为过时
        return NoOpPasswordEncoder.getInstance();
    }
}

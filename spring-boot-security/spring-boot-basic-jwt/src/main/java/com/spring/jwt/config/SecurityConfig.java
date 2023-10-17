package com.spring.jwt.config;

import com.spring.jwt.filter.JwtAuthenticationTokenFilter;
import com.spring.jwt.security.LoginFailureHandler;
import com.spring.jwt.security.LoginSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests->
                        authorizeHttpRequests
                                .requestMatchers("/index").hasAnyAuthority("admin:api","user:api")
                                .requestMatchers("/admin").hasAuthority("admin:api")
                                .requestMatchers("/user").hasAuthority("user:api")
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/user/save").permitAll()
                                .requestMatchers("/toLogin").permitAll()
                                .anyRequest().authenticated()

        );

        http.formLogin(formLogin->
                formLogin.loginPage("/toLogin")
                        .loginProcessingUrl("/login")
                        //.defaultSuccessUrl("/index") //
                        //.failureForwardUrl("/failre") // 只能是post
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailureHandler())
        );

        http.logout(logout->
                logout.logoutSuccessUrl("/logout")
        );

        // 登陆前获取token并校验
        http.addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(e->e.accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("异常："+accessDeniedException.getMessage());
            }
        }));

        //http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));

        http.csrf(c->c.disable());

        http.cors(e->e.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return  NoOpPasswordEncoder.getInstance();
    }
}

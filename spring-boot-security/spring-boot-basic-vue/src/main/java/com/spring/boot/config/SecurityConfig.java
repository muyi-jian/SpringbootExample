package com.spring.boot.config;

import com.spring.boot.security.LoginFailureHandler;
import com.spring.boot.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests->
                authorizeHttpRequests.requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin(formLogin->
                formLogin.loginProcessingUrl("/login").permitAll()
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailureHandler())
        );

        //http.csrf(csrf->csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(Customizer.withDefaults());
        //http.cors(cors->cors.disable());
        return http.build();
    }

}

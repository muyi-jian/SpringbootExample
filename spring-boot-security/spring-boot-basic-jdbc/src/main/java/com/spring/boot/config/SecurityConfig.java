package com.spring.boot.config;

import com.spring.boot.security.LoginFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

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
                        .failureHandler(new LoginFailureHandler())
                        .defaultSuccessUrl("/index")
        );

        // http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));
        http.exceptionHandling(e->e.accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                System.out.println("accessDeniedException=="+accessDeniedException);
                accessDeniedException.printStackTrace();
            }
        }));


        http.csrf(csrf->csrf.disable());

        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
    }

    //@Autowired
    //DataSource dataSource;

    //@Bean
    //public UserDetailsService userDetailsService(){
    //    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
    //    jdbcUserDetailsManager.setDataSource(dataSource);
    //
    //    UserDetails user1 = User.withUsername("admin").password("123").authorities("admin:api", "user:api").build();
    //    UserDetails user2 = User.withUsername("user").password("123").authorities("user:api").build();
    //    //要是数据库 创建数据
    //    if (!jdbcUserDetailsManager.userExists("admin") && !jdbcUserDetailsManager.userExists("user")){
    //        jdbcUserDetailsManager.createUser(user1);
    //        jdbcUserDetailsManager.createUser(user2);
    //    }
    //    return jdbcUserDetailsManager;
    //}

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 明文编码
        return NoOpPasswordEncoder.getInstance();
    }
}

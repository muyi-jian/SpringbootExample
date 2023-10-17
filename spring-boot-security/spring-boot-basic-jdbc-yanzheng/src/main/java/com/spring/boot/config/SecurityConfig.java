package com.spring.boot.config;

import com.spring.boot.filter.VerficationCodeFilter;
import com.spring.boot.security.LoginFailureHandler;
import com.spring.boot.token.SysPersistentTokenRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    SysPersistentTokenRepositoryImpl sysPersistentTokenRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeHttpRequests->
                (authorizeHttpRequests
                        // 角色
                        //.requestMatchers("/admin/api").hasRole("admin")
                        //.requestMatchers("/user/api").hasAnyRole("admin","user")
                        .requestMatchers("/admin/api").hasAuthority("admin:api")
                        .requestMatchers("/user/api").hasAnyAuthority("admin:api", "user:api")
                        .requestMatchers("/app/api").permitAll()
                        .requestMatchers("/test/?").hasAnyAuthority("admin:api")
                        .requestMatchers("/test/api/*/*").hasAnyAuthority("admin:api")
                        .requestMatchers("/test/a/**").hasAnyAuthority("admin:api")
                        .requestMatchers("/captcha/**").permitAll()
                        .requestMatchers("/app/logout").permitAll()
                        .requestMatchers("/app/invalidSession")).permitAll()
                        .requestMatchers("/kickout").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin(formLogin->
                formLogin.loginPage("/login")
                        .loginProcessingUrl("/login").permitAll()
                        .failureHandler(new LoginFailureHandler())
                        .defaultSuccessUrl("/index")
        );

         http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));
        //http.exceptionHandling(e->e.accessDeniedHandler(new AccessDeniedHandler() {
        //    @Override
        //    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //        System.out.println("accessDeniedException=="+accessDeniedException);
        //        accessDeniedException.printStackTrace();
        //    }
        //}));

        http.addFilterBefore(new VerficationCodeFilter(), UsernamePasswordAuthenticationFilter.class);

        //rememberMeParameter:表单中记住我的name
        //rememberMeCookieName:记录的cookie的名字
        //key:是cookie加密的秘钥
        http.rememberMe(e->
                e.rememberMeParameter("rememberMe")
                        .rememberMeCookieName("rememberMe")
                        .key("myKey")
                        .tokenRepository(sysPersistentTokenRepository)
        );

        //http.sessionManagement(e->e.invalidSessionStrategy(new InvalidSessionStrategy() {
        //    @Override
        //    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //        response.setContentType("text/html;charset=UTF-8");
        //        response.getWriter().write("会话失效");
        //    }
        //}));

        //http.sessionManagement(e->
        //        e.invalidSessionUrl("/app/invalidSession")
        //                .maximumSessions(1)
        //);

        http.sessionManagement(e->
                e.maximumSessions(1).sessionRegistry(sessionRegistry())

        );

        //http.logout(logout->logout.invalidateHttpSession(true));
        http.logout(logout->
                logout.invalidateHttpSession(true)
                        .deleteCookies("rememberMe")
                        //.logoutSuccessUrl("/app/logout")
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.setContentType("text/html;charSet=UTF-8");
                                response.getWriter().write("退出成功");
                            }
                        })

        );
        http.csrf(csrf->csrf.disable());

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

    /**
     * 通过SessionRegistry 可以获取待当前所有以及登录的永固，根据登录的用户获取它的session状态等等，
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
}

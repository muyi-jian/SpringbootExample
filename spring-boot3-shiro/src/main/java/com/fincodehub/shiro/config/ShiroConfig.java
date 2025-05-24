package com.fincodehub.shiro.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fincodehub.shiro.realm.MyRealm;
import jakarta.servlet.Filter;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.lang.io.ResourceUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ShiroConfig
 * @create 2025/5/24 23:16
 * @description <TODO description class purpose>
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    /**
     * SecurityManager 安全管理器
     *
     * @return org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @author YangJian
     * @date 2025/5/24 12:27
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 创建defaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 创建加密对象，并设置相关属性
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用sha256加密
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        // 迭代加密次数
        hashedCredentialsMatcher.setHashIterations(3);
        // 将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        //将myReaml存储到defaultWebSecurityManager中
        defaultWebSecurityManager.setRealm(myRealm);
        // 设置rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        // 设置缓存处理器
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        //返回
        return defaultWebSecurityManager;
    }
    //缓存管理器
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        InputStream is =null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheManager cacheManager = new CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }
    //cookie属性设置
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        return cookie;
    }
    //创建Shiro的cookie管理对象
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }


    // 6. 注册Shiro过滤器
    @Bean
    public FilterRegistrationBean<Filter> shiroFilterRegistration() {
        FilterRegistrationBean<Filter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new DelegatingFilterProxy("shiroFilter"));
        reg.addInitParameter("targetFilterLifecycle", "true");
        reg.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return reg;
    }
    // 1. 注册Shiro过滤器链
    @Bean(name = {"shiroFilterFactoryBean", "shiroFilter"})
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(defaultWebSecurityManager());
        factory.setLoginUrl("/login");
        // factory.setUnauthorizedUrl("/error/403");

        // 2. 动态权限规则（建议从数据库加载）
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/userLogin", "anon");
        filterMap.put("/login", "anon");
        // filterMap.put("/api/**", "jwt");  // JWT过滤器
        filterMap.put("/**", "authc");
        //添加存在用户的过滤器（rememberMe）
        filterMap.put("/**", "user");
        //登出
        filterMap.put("/logout", "logout");
        factory.setFilterChainDefinitionMap(filterMap);

        // 3. 注册自定义过滤器
        // Map<String, Filter> filters = new HashMap<>();
        // filters.put("jwt", new JwtFilter());
        // factory.setFilters(filters);
        return factory;
    }
    /**
     * 用于解析thymeleaf中的shiro:相关属性
     * @author YangJian
     * @date 2025/5/24 21:30
     * @return at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}

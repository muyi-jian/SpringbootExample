package com.fincodehub.web.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyOncePerRequestFilter
 * @create 2025/4/28 23:58
 * @description <TODO description class purpose>
 */
@Slf4j
public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // 请求预处理逻辑
        log.info("MyOncePerRequestFilter doFilter pre url:{}", request.getRequestURL());
        // 继续过滤器链
        filterChain.doFilter(request, response);
        // 响应后处理逻辑
        log.info("MyOncePerRequestFilter doFilter after");

    }
}

package com.fincodehub.web.filter;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyFilter2
 * @create 2025/4/28 23:27
 * @description <TODO description class purpose>
 */
@Slf4j
public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 请求前处理
        log.info("MyFilter2 doFilter pre");
        filterChain.doFilter(servletRequest, servletResponse);
        // 响应后处理
        log.info("MyFilter2 doFilter after");
    }
}

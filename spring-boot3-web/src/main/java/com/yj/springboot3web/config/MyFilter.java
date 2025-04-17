package com.yj.springboot3web.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyFilter
 * @create 2025/4/16 8:34
 * @description <TODO description class purpose>
 */
public class MyFilter implements Filter {
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) srequest;
        System.out.println("this is MyFilter,url :"+request.getRequestURI());
        String ip = "";
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            ip = request.getRemoteAddr(); // 可能在某些直接连接的情况下使用
        }else {
            ip = xfHeader.split(",")[0]; // 获取第一个IP地址，通常是客户端的真实IP
        }
        System.out.println("ip:"+ip);
        filterChain.doFilter(srequest, sresponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}
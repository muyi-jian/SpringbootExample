package com.spring.jwt.filter;

import com.spring.jwt.utils.JWTutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        System.out.println("token============="+token);

        if (token != null){
            try {
                JWTutil.tokenVerify(token);
            } catch (Exception e) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("非法token");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }
}

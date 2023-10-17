package com.spring.boot.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("loginOk");
        System.out.println("authentication.getPrincipal()====="+authentication.getPrincipal());
        System.out.println("authentication.getPrincipal()====="+authentication.getAuthorities());
        System.out.println("authentication.getPrincipal()====="+authentication.getCredentials());

    }
}

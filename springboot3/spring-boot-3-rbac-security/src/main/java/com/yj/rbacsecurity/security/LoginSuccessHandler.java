package com.yj.rbacsecurity.security;

import com.alibaba.fastjson.JSON;
import com.yj.rbacsecurity.utils.JWTutil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        String token = JWTutil.token(authentication);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("token",token);
        response.getWriter().write(JSON.toJSONString(tokenInfo));
    }
}

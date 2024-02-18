package com.yj.rbacsecurity.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class JWTutil {
    private  static  final String secret = "111111";

    public static String token(Authentication authentication){
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+1000L*60*60*24*30))
                .withAudience(JSON.toJSONString(authentication)) //设置接受方信息 一般指登录用户
                .sign(Algorithm.HMAC256(secret));
    }

    public static void tokenVerify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtVerifier.verify(token);//没报错说明验证成功




        JWT.decode(token).getExpiresAt();

        String json = JWT.decode(token).getAudience().get(0);

        JwtAuthentication jwtAuthentication = JSON.parseObject(json, JwtAuthentication.class);
        SecurityContextHolder.getContext().setAuthentication((Authentication) jwtAuthentication);

    }
}

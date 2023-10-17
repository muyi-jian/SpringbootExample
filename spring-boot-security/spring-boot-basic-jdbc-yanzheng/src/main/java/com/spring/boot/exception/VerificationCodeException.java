package com.spring.boot.exception;


import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException(){
        super("图形验证码验证失败");
    }
}

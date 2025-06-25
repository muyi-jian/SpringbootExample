package com.fincodehub.problemdetails.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

/**
 * @author YangJian
 * @version 1.0.0
 * @title GlobalExceptionHandler
 * @create 2025/6/25 21:58
 * @description <TODO description class purpose>
 */
@RestControllerAdvice(basePackages = "com.fincodehub.problemdetails.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ProblemDetail handleCustomException(ArithmeticException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("服务器发生异常");
        problemDetail.setType(URI.create("https://problemdetails.com/errors/xxx"));
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
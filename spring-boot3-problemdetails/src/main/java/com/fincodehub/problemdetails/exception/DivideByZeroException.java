package com.fincodehub.problemdetails.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

/**
 * @author YangJian
 * @version 1.0.0
 * @title DivideByZeroException
 * @create 2025/6/25 22:05
 * @description <TODO description class purpose>
 */
public class DivideByZeroException extends ErrorResponseException {


    public DivideByZeroException(String customMsg) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(customMsg), null);
    }

    private static ProblemDetail asProblemDetail(String customMsg) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, customMsg);
        problemDetail.setTitle("除零异常");
        problemDetail.setType(URI.create("https://coderjia.cn/errors/xxx"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
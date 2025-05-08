package com.fincodehub.jpa.controller.exception;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YangJian
 * @version 1.0.0
 * @title GlobException
 * @create 2025/5/8 22:12
 * @description <TODO description class purpose>
 */
@RestControllerAdvice
public class GlobException {

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // HTTP状态码409表示冲突
    public String handleOptimisticLockException(OptimisticLockingFailureException ex) {
        return "数据已被其他用户修改，请刷新后重试";
    }
}

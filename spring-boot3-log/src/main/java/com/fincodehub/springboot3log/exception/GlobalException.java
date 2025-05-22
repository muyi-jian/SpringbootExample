package com.fincodehub.springboot3log.exception;


import com.fincodehub.springboot3log.util.ResultObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YangJian
 * @version 1.0.0
 * @title GlobalException
 * @create 2025/5/22 20:05
 * @description <TODO description class purpose>
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResultObject handleException(Exception e) {
        return ResultObject.fail(e.getMessage());
    }
}

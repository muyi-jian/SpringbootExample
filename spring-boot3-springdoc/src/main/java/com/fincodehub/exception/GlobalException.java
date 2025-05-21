package com.fincodehub.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YangJian
 * @version 1.0.0
 * @title GlobalException
 * @create 2025/5/21 21:07
 * @description <TODO description class purpose>
 */
@RestControllerAdvice
// @Hidden
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);


    @ExceptionHandler(ServiceException.class)
    public String handleException(ServiceException e) {
        Integer code = e.getCode();
        log.error("业务异常code::", code);
        return "ServiceException异常";
    }
}

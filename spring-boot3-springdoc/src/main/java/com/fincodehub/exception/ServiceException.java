package com.fincodehub.exception;


/**
 * @author YangJian
 * @version 1.0.0
 * @title ServiceException
 * @create 2025/5/21 21:09
 * @description <TODO description class purpose>
 */
public class ServiceException extends RuntimeException{
    private Integer  code;

    public ServiceException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ServiceException(String message){
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}

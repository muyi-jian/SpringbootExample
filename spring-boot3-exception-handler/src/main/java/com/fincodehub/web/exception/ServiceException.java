package com.fincodehub.web.exception;


/**
 * @author YangJian
 * @version 1.0.0
 * @title ServiceException
 * @create 2025/4/28 22:46
 * @description <TODO description class purpose>
 */

public class ServiceException extends RuntimeException{
    // 异常状态码（可根据业务需求定义为枚举）
    private Integer code;

    // 全参构造器
    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    // 仅消息构造器（code默认为null，由全局处理器判断）
    public ServiceException(String message) {
        super(message);
    }

    // Getter（全局处理器通过 getCode() 读取状态码）
    public Integer getCode() {
        return code;
    }
}

/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:16
 * @version 1.0.0
 */
package com.yj.exception.constant;

import lombok.Getter;

/**
 * 状态码封装
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:16
 * @version 1.0.0
 */
@Getter
public enum Status {

    /**
     * 操作成功
     */
    OK(200, "操作成功"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;
    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.yj.jdbctemplate.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回类
 *
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/18 20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private Boolean status;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 全参数方法
     *
     * @param code    状态码
     * @param status  状态
     * @param message 返回信息
     * @param data    返回数据
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    private static <T> ResponseObject<T> response(Integer code, Boolean status, String message, T data) {
        ResponseObject<T> ResponseObject = new ResponseObject<>();
        ResponseObject.setCode(code);
        ResponseObject.setStatus(status);
        ResponseObject.setMessage(message);
        ResponseObject.setData(data);
        return ResponseObject;
    }

    /**
     * 全参数方法
     *
     * @param code    状态码
     * @param status  状态
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    private static <T> ResponseObject<T> response(Integer code, Boolean status, String message) {
        ResponseObject<T> ResponseObject = new ResponseObject<>();
        ResponseObject.setCode(code);
        ResponseObject.setStatus(status);
        ResponseObject.setMessage(message);
        return ResponseObject;
    }

    /**
     * 成功返回（无参）
     *
     * @param <T> 泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success() {
        return response(StatusEnum.SUCCESS.getCode(), true, StatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回（枚举参数）
     *
     * @param httpResponseEnum 枚举参数
     * @param <T>              泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(StatusEnum httpResponseEnum) {
        return response(httpResponseEnum.getCode(), true, httpResponseEnum.getMessage());
    }

    /**
     * 成功返回（状态码+返回信息）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(Integer code, String message) {
        return response(code, true, message);
    }

    /**
     * 成功返回（返回信息 + 数据）
     *
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(String message, T data) {
        return response(StatusEnum.SUCCESS.getCode(), true, message, data);
    }

    /**
     * 成功返回（状态码+返回信息+数据）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(Integer code, String message, T data) {
        return response(code, true, message, data);
    }

    /**
     * 成功返回（数据）
     *
     * @param data 数据
     * @param <T>  泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(T data) {
        return response(StatusEnum.SUCCESS.getCode(), true, StatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回（返回信息）
     *
     * @param message 返回信息
     * @param <T>  泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> success(String message) {
        return response(StatusEnum.SUCCESS.getCode(), true, message, null);
    }

    /**
     * 失败返回（无参）
     *
     * @param <T> 泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail() {
        return response(StatusEnum.ERROR.getCode(), false, StatusEnum.ERROR.getMessage(), null);
    }

    /**
     * 失败返回（枚举）
     *
     * @param httpResponseEnum 枚举
     * @param <T>              泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(StatusEnum httpResponseEnum) {
        return response(httpResponseEnum.getCode(), false, httpResponseEnum.getMessage());
    }

    /**
     * 失败返回（状态码+返回信息）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(Integer code, String message) {
        return response(code, false, message);
    }

    /**
     * 失败返回（返回信息+数据）
     *
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(String message, T data) {
        return response(StatusEnum.ERROR.getCode(), false, message, data);
    }

    /**
     * 失败返回（状态码+返回信息+数据）
     *
     * @param code    状态码
     * @param message 返回消息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(Integer code, String message, T data) {
        return response(code, false, message, data);
    }

    /**
     * 失败返回（数据）
     *
     * @param data 数据
     * @param <T>  泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(T data) {
        return response(StatusEnum.ERROR.getCode(), false, StatusEnum.ERROR.getMessage(), data);
    }

    /**
     * 失败返回（返回信息）
     *
     * @param message 返回信息
     * @param <T>  泛型
     * @return {@link ResponseObject<T>}
     */
    public static <T> ResponseObject<T> fail(String message) {
        return response(StatusEnum.ERROR.getCode(), false, message, null);
    }
}

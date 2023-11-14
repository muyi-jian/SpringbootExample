package com.yj.result.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultUtil<T> implements Serializable {
    private Integer code;

    private String message;

    private T data;

    public ResultUtil() {
    }

    public static <T> ResultUtil<T> success(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResultEnum.SUCCESS.getCode());
        resultUtil.setMessage(ResultEnum.SUCCESS.getMsg());
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(T data){
        ResultUtil<T> resultUtil = success();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(String message,T data){
        ResultUtil<T> resultUtil = success();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }
    public static <T> ResultUtil<T> success(Integer code,String message,T data){
        ResultUtil<T> resultUtil = success();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResultEnum.FAIL.getCode());
        resultUtil.setMessage(ResultEnum.FAIL.getMsg());
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(T data){
        ResultUtil<T> resultUtil = fail();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(String message,T data){
        ResultUtil<T> resultUtil = fail();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }
    public static <T> ResultUtil<T> fail(Integer code,String message,T data){
        ResultUtil<T> resultUtil = fail();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }
}

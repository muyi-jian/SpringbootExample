package com.yj.result.utils;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),

    FAIL(4000, "失败"),
    SUCCESS(2000, "成功");



    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

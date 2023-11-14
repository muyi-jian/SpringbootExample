package com.yj.result.exception;

public class MyException extends RuntimeException{

    private long code;
    private String msg;

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(long code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }



    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

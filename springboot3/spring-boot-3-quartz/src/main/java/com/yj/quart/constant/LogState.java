package com.yj.quart.constant;

/**
 * @author YangJian
 * @date 2024/1/1 17:53
 * @description
 */
public enum LogState {

    LOG_SUS(1,"成功"),
    LOG_FAIL(2,"失败");

    private int state;
    private String desc;

    public int getState(){
        return state;
    }

    public String getDesc(){
        return desc;
    }

    LogState(int state, String desc){
        this.state = state;
        this.desc = desc;
    }
    
}

package com.yj.quart.constant;

/**
 * @author YangJian
 * @date 2024/1/1 17:40
 * @description
 */
public enum JobState {
    JOB_RUN(1, "运行"),
    JOB_STOP(2, "暂停"),
    JOB_DEL(3, "删除");

    private int state;
    private String desc;

    JobState(int state, String desc){
        this.state = state;
        this.desc = desc;
    }

    public int getState(){
        return state;
    }

    public String getDesc(){
        return desc;
    }


}

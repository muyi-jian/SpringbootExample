package com.yj.quart.job;

/**
 * Job触发器
 *
 * @author YangJian
 * @date 2024/1/2 9:37
 * @description
 */
public interface JobService {
    void run(String params);
}

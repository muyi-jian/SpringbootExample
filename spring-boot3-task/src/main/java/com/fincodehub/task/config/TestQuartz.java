package com.fincodehub.task.config;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title TestQuartz
 * @create 2025/5/17 23:29
 * @description <TODO description class purpose>
 */
@Component
public class TestQuartz implements Job {

    /**
     * 执行定时任务
     * @author YangJian
     * @date 2025/5/17 23:40
     * @param context
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("quartz task  -- "+ context.getFireTime());
    }
}

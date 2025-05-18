package com.fincodehub.task.service;


import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title QuartzService
 * @create 2025/5/18 0:01
 * @description <TODO description class purpose>
 */
@Service
public class QuartzService {
    private final Scheduler scheduler;

    @Autowired
    public QuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 添加调度任务
     *
     * @param jobDetail 任务详情
     * @param trigger   触发器
     */
    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停任务
     * @author YangJian
     * @date 2025/5/18 0:03
     * @param jobName
     * @param jobGroup
     */
    public void pauseJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            scheduler.pauseJob(jobKey);
            System.out.println("Job paused: " + jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     * @author YangJian
     * @date 2025/5/18 0:03
     * @param jobName
     * @param jobGroup
     */
    public void resumeJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            scheduler.resumeJob(jobKey);
            System.out.println("Job resumed: " + jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除任务
     * @author YangJian
     * @date 2025/5/18 0:04
     * @param jobName
     * @param jobGroup
     */
    public void deleteJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            boolean deleted = scheduler.deleteJob(jobKey);
            if (deleted) {
                System.out.println("Job deleted: " + jobKey);
            } else {
                System.out.println("Job not found: " + jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

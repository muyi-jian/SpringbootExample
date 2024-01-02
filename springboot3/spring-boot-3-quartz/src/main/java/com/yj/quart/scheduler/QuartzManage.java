package com.yj.quart.scheduler;

import com.yj.quart.constant.JobState;
import com.yj.quart.entity.QuartzJob;
import jakarta.annotation.Resource;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * 任务基础操作
 * @author YangJian
 * @date 2024/1/2 9:19
 * @description
 */
@Component
public class QuartzManage {
    private static final String SCHEDULE_NAME = "BOOT_JOB_" ;


    @Resource
    private Scheduler scheduler;
    /**
     * 创建任务
     * @author YangJian
     * @date 2024/1/2 10:37
     * @param quartzJob
     */
    public void createJob(QuartzJob quartzJob) {

        try {
            // 构建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzRecord.class)
                    .withIdentity(getJobKey(quartzJob.getId())).build();
            // 构建Cron调度器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(quartzJob.getCronExpres())
                    .withMisfireHandlingInstructionDoNothing();

            // 任务触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(quartzJob.getId()))
                    .withSchedule(scheduleBuilder).build();

            jobDetail.getJobDataMap().put(QuartzJob.JOB_PARAM_KEY,quartzJob);
            scheduler.scheduleJob(jobDetail, trigger);
            // 状态校验
            checkStop(quartzJob);
        } catch (SchedulerException e) {
            throw new RuntimeException("createJob Fail",e) ;
        }
    }
    /**
     * 更新任务
     * @author YangJian
     * @date 2024/1/2 10:37
     * @param quartzJob
     */
    public void updateJob(QuartzJob quartzJob) {

        try {
            // 查询触发器Key
            TriggerKey triggerKey = getTriggerKey(quartzJob.getId());
            // 构建Cron调度器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(quartzJob.getCronExpres())
                    .withMisfireHandlingInstructionDoNothing();

            // 任务触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(quartzJob.getId()))
                    .withSchedule(scheduleBuilder).build();

            trigger.getJobDataMap().put(QuartzJob.JOB_PARAM_KEY,quartzJob);
            scheduler.rescheduleJob(triggerKey, trigger);
            // 状态校验
            checkStop(quartzJob);
        } catch (SchedulerException e) {
            throw new RuntimeException("createJob Fail",e) ;
        }
    }

    /** 
     * 恢复定时器
     * @author YangJian
     * @date 2024/1/2 10:50
     * @param jobId 
     
     */
    public void resumeJob(Integer jobId){
        try {
            this.scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("resumeJob Fail",e);
        }
    }
    /**
     * 删除定时器
     * @author YangJian
     * @date 2024/1/2 10:50
     * @param jobId

     */
    public void delJob(Integer jobId){
        try {
            this.scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("deleteJob Fail",e);
        }
    }

    /**
     * 执行定时器
     * @author YangJian
     * @date 2024/1/2 10:54
     * @param quartzJob
     */

    public void run(QuartzJob quartzJob){
        try {
            JobDataMap dataMap = new JobDataMap();
            System.out.println("quartzJob========="+quartzJob);
            dataMap.put(QuartzJob.JOB_PARAM_KEY, quartzJob);

            this.scheduler.triggerJob(getJobKey(quartzJob.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("run Fail",e);
        }
    }

    /**
     * 校验停止定时器
     * @author YangJian
     * @date 2024/1/2 10:31
     * @param quartzJob
     */
    public void checkStop(QuartzJob quartzJob) {
        if (quartzJob.getState() != JobState.JOB_RUN.getState()){
            try {
                this.scheduler.pauseJob(getJobKey(quartzJob.getId()));
            } catch (SchedulerException e) {
                throw new RuntimeException("pauseJob Fail",e);
            }
        }
    }

    /**
     * 触发器key
     * @author YangJian
     * @date 2024/1/2 10:29
     * @param jobId 任务key
     * @return String
     */
    private TriggerKey getTriggerKey(Integer jobId) {
        return TriggerKey.triggerKey(SCHEDULE_NAME+jobId);
    }

    public CronTrigger getCronTrigger(Integer jobId){
        try {
            return (CronTrigger) this.scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("getCronTrigger Fail",e);
        }
    }

    /**
     * 定时任务key
     * @author YangJian
     * @date 2024/1/2 10:28
     * @param jobId 任务ID
     * @return JobKey
     */
    private JobKey getJobKey(Integer jobId) {
        return JobKey.jobKey(SCHEDULE_NAME+jobId);
    }
}

package com.yj.quart.scheduler;

import com.yj.quart.constant.LogState;
import com.yj.quart.entity.QuartzJob;
import com.yj.quart.entity.QuartzLog;
import com.yj.quart.service.QuartzLogService;
import jakarta.annotation.Resource;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 任务记录
 * @author YangJian
 * @date 2024/1/2 10:17
 * @description
 */
public class QuartzRecord extends QuartzJobBean {


    @Resource
    QuartzLogService quartzLogService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob)context.getMergedJobDataMap().get(QuartzJob.JOB_PARAM_KEY) ;
        // 定时器日志记录
        QuartzLog quartzLog = new QuartzLog() ;
        quartzLog.setJobId(quartzJob.getId());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setParams(quartzJob.getParams());
        quartzLog.setCreateTime(new Date());
        long beginTime = System.currentTimeMillis() ;
        try {
            // 加载并执行
            Object target = SpringContextUtil.getBean(quartzJob.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, quartzJob.getParams());
            long executeTime = System.currentTimeMillis() - beginTime;
            quartzLog.setTimes((int)executeTime);
            quartzLog.setState(LogState.LOG_SUS.getState());
        } catch (Exception e){
            // 异常信息
            long executeTime = System.currentTimeMillis() - beginTime;
            quartzLog.setTimes((int)executeTime);
            quartzLog.setState(LogState.LOG_FAIL.getState());
            quartzLog.setError(e.getMessage());
        } finally {
            // 保存执行日志
            quartzLogService.insert(quartzLog) ;
        }
    }

}

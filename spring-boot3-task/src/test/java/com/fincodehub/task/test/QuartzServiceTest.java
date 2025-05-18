package com.fincodehub.task.test;


import com.fincodehub.task.service.QuartzService;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author YangJian
 * @version 1.0.0
 * @title QuartzServiceTest
 * @create 2025/5/18 0:11
 * @description <TODO description class purpose>
 */
public class QuartzServiceTest {
    private final QuartzService quartzService;

    public QuartzServiceTest() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        this.quartzService = new QuartzService(scheduler);
    }

    @Test
    public void testJobLifecycle() throws InterruptedException {
        // 1. 创建测试任务
        JobDetail job = newJob(TestJob.class)
                .withIdentity("testJob", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("testTrigger", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // 2. 添加任务
        quartzService.scheduleJob(job, trigger);
        System.out.println("Job add  scheduled");
        Thread.sleep(3000); // 运行3秒

        // 3. 暂停任务
        quartzService.pauseJob("testJob", "group1");
        System.out.println("Job paused for 2 seconds");
        Thread.sleep(2000);

        // 4. 恢复任务
        quartzService.resumeJob("testJob", "group1");
        System.out.println("Job resumed");
        Thread.sleep(3000);

        // 5. 删除任务
        quartzService.deleteJob("testJob", "group1");
        System.out.println("Job deleted");
    }
    public static class TestJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Job executed at: " + new java.util.Date());
        }
    }
}

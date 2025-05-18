package com.fincodehub.task.config;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author YangJian
 * @version 1.0.0
 * @title QuartzConfig
 * @create 2025/5/17 23:30
 * @description <TODO description class purpose>
 */

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(TestQuartz.class)
                .withIdentity("TestQuartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        // SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
        //         .withIntervalInSeconds(10)  // 每 10 秒执行一次
        //         .repeatForever();

        // return TriggerBuilder.newTrigger()
        //         .forJob(sampleJobDetail())
        //         .withIdentity("sampleJobTrigger")
        //         .withSchedule(scheduleBuilder)
        //         .build();

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");

        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleJobTrigger")
                .withSchedule(cronScheduleBuilder)  // 每分钟执行一次
                .build();

    }
}

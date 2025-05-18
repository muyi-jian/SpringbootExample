package com.fincodehub.task.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SchedulerConfig
 * @create 2025/5/17 22:49
 * @description <TODO description class purpose>
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    /*
     *此处成员变量应该使用@Value从配置中读取
     */
    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public ThreadPoolTaskScheduler taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(corePoolSize); // 设置线程池大小
        executor.setThreadNamePrefix("my-scheduled-task-"); // 设置线程名前缀
        executor.setWaitForTasksToCompleteOnShutdown(true); // 优雅关闭
        executor.setAwaitTerminationSeconds(60); // 等待任务完成超时时间
        executor.initialize();
        return executor;
    }

}
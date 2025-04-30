package com.fincodehub.quarz.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author YangJian
 * @version 1.0.0
 * @title SchedulerTask
 * @create 2025/4/30 11:17
 * @description <TODO description class purpose>
 */
@Component
public class SchedulerTask {
    private int count=0;

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }
}

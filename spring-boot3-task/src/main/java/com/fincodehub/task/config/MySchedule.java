package com.fincodehub.task.config;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MySchedule
 * @create 2025/5/17 22:21
 * @description <TODO description class purpose>
 */
@Component
public class MySchedule {

    @Scheduled(fixedDelay = 5000)//代表上一次执行完毕 5秒后在执行
    public void fixedDelay() {

        System.out.println("Scheduled   上一次执行完毕:"+ new Date());
    }
    @Scheduled(fixedRate = 2000)//上一次开始执行时间点之后2秒再执行
    public void rateDelay() {
        System.out.println("Scheduled   任务执行 - 线程: " + Thread.currentThread().getName());

        System.out.println("Scheduled   上一次开始执行时间点之后:"+ new Date());
    }

    @Scheduled(initialDelay = 2000,fixedDelay = 5000)//第一次延迟启动2秒在执行,再按每次执行完后5秒执行
    public void initialDelay() {

        System.out.println("Scheduled   延迟启动:"+ new Date());
    }

    @Scheduled(cron = "0 * * * * ?")//cron表达式 表示每分钟执行一次
    public void cornDelay() {
        System.out.println("Scheduled   cron:"+ new Date());
    }

}

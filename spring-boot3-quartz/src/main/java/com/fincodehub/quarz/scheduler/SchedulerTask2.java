package com.fincodehub.quarz.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SchedulerTask2
 * @create 2025/4/30 11:17
 * @description <TODO description class purpose>
 */
@Component
public class SchedulerTask2 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

}
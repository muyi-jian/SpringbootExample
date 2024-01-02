package com.yj.quart.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Job触发器
 *
 * @author YangJian
 * @date 2024/1/2 9:37
 * @description
 */
@Component("timerJob")
public class TimerJob implements JobService {

    private static final Logger log = LoggerFactory.getLogger(TimerJob.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    @Override
    public void run(String params) {
        log.info("\n ======== \n timer-job-params:{} \n ========",params);
        log.info("time-now:{}",format.format(new Date()));
    }
}

package com.yj.quart.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Job触发器
 *
 * @author YangJian
 * @date 2024/1/2 9:37
 * @description
 */
@Component("printJob")
public class PrintJob implements JobService {

    private static final Logger log = LoggerFactory.getLogger(PrintJob.class);

    @Override
    public void run(String params) {
        log.info("\n ======== \n print-job-params:{} \n ========",params);
    }
}

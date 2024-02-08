package com.yj.xxljob.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/2/8 11:27
 */
@Slf4j
@Component
public class Job {
    @XxlJob("testJob")
    public ReturnT<String> testJob(String date) {
        log.info("---------testJob定时任务执行成功--------");
        XxlJobHelper.log("---------testJob定时任务执行成功--------");
        return ReturnT.SUCCESS;
    }
}

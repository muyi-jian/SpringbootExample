/**
 * 测试日志输出
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/15 9:47
 * @version 1.0.0
 */
package com.yj.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试日志输出
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/15 9:47
 * @version 1.0.0
 */
@RestController
public class TestLogController {
    Logger logger = LoggerFactory.getLogger(TestLogController.class);
    @GetMapping("/soutLog")
    public String soutLog(){
        logger.info("ALL：打印所有日志");
        logger.trace("TRACE：追踪框架详细流程日志，一般不使用");
        logger.debug("DEBUG：开发调试细节日志");
        logger.info("INFO：关键、感兴趣信息日志");
        logger.warn("WARN：警告但不是错误的信息日志，比如：版本过时");
        logger.error("ERROR：业务错误日志，比如出现各种异常");
        logger.info("FATAL：致命错误日志，比如jvm系统崩溃");
        logger.info("OFF：关闭所有日志记录");
        return "ok";
    }
}

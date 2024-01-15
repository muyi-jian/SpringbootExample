/**
 * 测试日志输出
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/15 9:47
 * @version 1.0.0
 */
package com.yj.logging.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestLogAnnoController {
    @GetMapping("/soutLogAnno")
    public String soutLog(){
        //打印日志
        log.info("注解................................................................");
        log.info("ALL：打印所有日志");
        log.trace("TRACE：追踪框架详细流程日志，一般不使用");
        log.debug("DEBUG：开发调试细节日志");
        log.info("INFO：关键、感兴趣信息日志");
        log.warn("WARN：警告但不是错误的信息日志，比如：版本过时");
        log.error("ERROR：业务错误日志，比如出现各种异常");
        log.info("FATAL：致命错误日志，比如jvm系统崩溃");
        log.info("OFF：关闭所有日志记录");
        log.info("注解................................................................");

        return "ok";
    }
}

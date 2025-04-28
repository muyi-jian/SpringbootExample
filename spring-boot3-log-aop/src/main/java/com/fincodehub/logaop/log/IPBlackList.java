package com.fincodehub.logaop.log;


import java.lang.annotation.*;

/**
 * @author YangJian
 * @version 1.0.0
 * @title IPBlacklist
 * @create 2025/4/24 23:13
 * @description <TODO description class purpose>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IPBlackList {
    int maxRequests() default 10;// 最大请求次数
    long timeWindow() default 60000L; // 计数时间窗口，单位毫秒
    long blockTime() default 60000L; // 拉黑时间 单位毫秒

}

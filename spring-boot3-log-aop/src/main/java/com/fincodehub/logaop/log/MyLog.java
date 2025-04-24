package com.fincodehub.logaop.log;


import java.lang.annotation.*;

/**
 *
 * @author YangJian
 * @version 1.0.0
 * @title MyLog
 * @create 2025/4/24 22:49
 * @description 日志记录注解
 * `@Target`：限制注解只能用于方法。
 * `@Retention`：确保注解在运行时可用，便于 AOP 或反射处理。
 * `@Documented`：使注解出现在 Javadoc 中。
 * `value` 属性：提供灵活的日志消息配置。
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}

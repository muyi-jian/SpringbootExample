package com.yj.result.annotaion;

import java.lang.annotation.*;

/**
 * 不需要返回特定格式的数据使用此注解
 */
@Documented
@Inherited
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseHandler {
}

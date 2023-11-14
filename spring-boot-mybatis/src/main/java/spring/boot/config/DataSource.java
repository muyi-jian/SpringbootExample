package spring.boot.config;

/**
 * 自定义数据源切换的注解
 * @author yangjian
 * @date 2022/11/24 16:27
 */

import java.lang.annotation.*;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    public DataSourceType value() default DataSourceType.MASTER;
}
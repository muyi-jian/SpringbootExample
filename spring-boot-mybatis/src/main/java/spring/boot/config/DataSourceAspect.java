package spring.boot.config;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * 配置切面拦截
 * @author yangjian
 * @date 2022/11/24 16:32
 */
@Aspect
@Order(1) // 优先级
@Component
public class DataSourceAspect {

    /**
     * 通过自定义注解@DataSource定义切点
     */
    @Pointcut("@annotation(spring.boot.config.DataSource)"
            + "|| @within(spring.boot.config.DataSource)")
    public void dsPointCut() {
    }

    /**
     * 切点环绕
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);
        if (dataSource!=null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (targetDataSource!=null) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }
}
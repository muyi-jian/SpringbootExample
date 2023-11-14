package spring.boot.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源切换上下文
 * @author yangjian
 * @date 2022/11/24 16:37
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        log.info("切换到{}数据库",dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
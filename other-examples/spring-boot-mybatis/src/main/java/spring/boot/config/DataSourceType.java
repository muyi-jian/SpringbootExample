package spring.boot.config;

/**
 * 数据源类型枚举类
 * @author yangjian
 * @date 2022/11/24 16:28
 */
public enum DataSourceType {
    /**
     * 主库
     */
    MASTER,

    /**
     * 从库
     */
    SLAVE
}

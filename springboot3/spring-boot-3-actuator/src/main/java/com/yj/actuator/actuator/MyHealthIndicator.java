/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 13:15
 * @version 1.0.0
 */
package com.yj.actuator.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义自定义健康端点
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 13:15
 * @version 1.0.0
 */
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // 在这里编写你的健康检查逻辑
        boolean isHealthy = check();
        if (isHealthy) {
            return Health.up()
                    .withDetail("code",200)
                    .withDetail("msg","我很健康")
                    .withDetail("data","我的名字叫健康")
                    .build();
        } else {
            return Health.down()
                    .withDetail("code",400)
                    .withDetail("msg","我挂掉了")
                    .withDetail("data","我的名字叫完蛋")
                    .build();
        }
    }

    /**
     * 健康检查逻辑
     * @param
     * @date  2024/1/16 13:24
     * @return boolean
     */
    private boolean check() {
        // 假设服务是健康的
        return true;
    }
}

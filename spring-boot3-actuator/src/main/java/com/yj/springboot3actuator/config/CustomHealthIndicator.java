package com.yj.springboot3actuator.config;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title CustomHealthIndicator
 * @create 2025/4/14 19:52
 * @description <TODO description class purpose>
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 自定义健康检查逻辑
        boolean serviceRunning = checkExternalService();
        if (serviceRunning) {
            return Health.up().withDetail("service", "running").build();
        } else {
            return Health.down().withDetail("service", "stopped").build();
        }
    }

    private boolean checkExternalService() {
        // 模拟外部服务的检查
        return true;
    }
}
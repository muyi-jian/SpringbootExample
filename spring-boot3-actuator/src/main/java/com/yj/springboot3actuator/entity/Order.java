package com.yj.springboot3actuator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @version 1.0.0
 * @title Order
 * @create 2025/4/15 10:39
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
public class Order {
    private String orderId;
    private String userId;
    private String orderName;
}

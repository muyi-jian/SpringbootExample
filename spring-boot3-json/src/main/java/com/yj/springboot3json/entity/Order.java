package com.yj.springboot3json.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @version 1.0.0
 * @title Oder
 * @create 2025/4/12 21:57
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
public class Order {
    private String orerNo;
    private String orderName;
    private Double amount;
}

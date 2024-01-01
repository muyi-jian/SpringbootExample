package com.yj.swagger.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangJian
 * @date 2023/12/31 10:35
 * @description
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private int age;
    private String address;
}

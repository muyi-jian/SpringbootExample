package com.fincodehub.shiro.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/5/24 12:07
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private String salt;
    private Integer rid;
    private Date createTime;
    private Date updateTime;
    private Boolean  isLocked;
}

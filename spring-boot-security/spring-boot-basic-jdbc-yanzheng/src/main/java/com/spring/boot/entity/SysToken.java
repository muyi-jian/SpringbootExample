package com.spring.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("t_sys_token")
@Data
public class SysToken {
    @TableField("username")
    private String username;

    @TableField("series")
    private String series;

    @TableField("token")
    private String tokenValue;

    @TableField("last_used")
    private Date date;
}

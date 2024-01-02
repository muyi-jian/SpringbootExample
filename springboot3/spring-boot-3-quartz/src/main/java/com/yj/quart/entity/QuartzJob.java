package com.yj.quart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 公众号:知了一笑
 * @since 2023-07-26 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("quartz_job")
public class QuartzJob implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "BOOT_JOB_PARAM_KEY";

    // "任务id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    // "Bean名称"
    private String beanName;

    // "执行参数"
    private String params;

    // "Cron表达式"
    private String cronExpres;

    // "任务状态：1正常，2暂停，3删除"
    private Integer state;

    // "备注"
    private String remark;

    // "创建时间"
    private Date createTime;
}
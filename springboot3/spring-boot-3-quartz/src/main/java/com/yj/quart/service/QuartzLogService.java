package com.yj.quart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.quart.entity.QuartzLog;

/**
 * @author YangJian
 * @date 2024/1/2 9:11
 * @description
 */
public interface QuartzLogService extends IService<QuartzLog> {

    Integer insert(QuartzLog quartzLog);
}

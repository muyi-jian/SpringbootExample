package com.yj.quart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.quart.entity.QuartzJob;

/**
 * @author YangJian
 * @date 2024/1/1 17:50
 * @description
 */
public interface QuartzJobService extends IService<QuartzJob> {

    Integer insert(QuartzJob quartzJob);

    void init ();
    QuartzJob getById(Integer id);
    int update(QuartzJob quartzJob);
    void pause(Integer id);
    void resume(Integer id);
    void runOnce(Integer id);
}

package com.yj.quart.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.quart.constant.JobState;
import com.yj.quart.entity.QuartzJob;
import com.yj.quart.mapper.QuartzJobMapper;
import com.yj.quart.scheduler.QuartzManage;
import com.yj.quart.service.QuartzJobService;
import jakarta.annotation.Resource;
import org.quartz.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author YangJian
 * @date 2024/1/1 17:50
 * @description
 */
@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {


    @Resource
    public QuartzManage quartzManage;
    /**
     * 新增定时任务
     * @author YangJian
     * @date 2024/1/2 11:06
     * @param quartzJob
     * @return Integer
     */
    @Override
    public Integer insert(QuartzJob quartzJob) {
        int num = baseMapper.insert(quartzJob);
        System.out.println("insert========="+num);
        if (num > 0){
            quartzManage.createJob(quartzJob) ;
        }
        return num;
    }

    /**
     * 初始化加载定时器
     * @author YangJian
     * @date 2024/1/2 11:04
     */
    @Override
    public void init() {

        LambdaQueryWrapper<QuartzJob> queryWrapper = new LambdaQueryWrapper<>() ;
        queryWrapper.in(QuartzJob::getState, JobState.JOB_RUN.getState(),JobState.JOB_STOP.getState());
        List<QuartzJob> jobList = baseMapper.selectList(queryWrapper);
        jobList.forEach(quartzJob -> {
            CronTrigger cronTrigger = quartzManage.getCronTrigger(quartzJob.getId()) ;
            if (Objects.isNull(cronTrigger)){
                quartzManage.createJob(quartzJob);
            } else {
                quartzManage.updateJob(quartzJob);
            }
        });


    }

    /**
     * 任务主键查询
     * @author YangJian
     * @date 2024/1/2 11:05
     * @param id
     * @return QuartzJob
     */
    @Override
    public QuartzJob getById(Integer id) {
        return baseMapper.selectById(id);
    }

    /**
     * 更新定时任务
     * @author YangJian
     * @date 2024/1/2 11:06
     * @param quartzJob
     * @return int
     */
    @Override
    public int update(QuartzJob quartzJob) {
        int num = baseMapper.updateById(quartzJob);
        if (num > 0){
            quartzManage.updateJob(quartzJob);
        }
        return num ;
    }

    /**
     * 暂停任务
     * @author YangJian
     * @date 2024/1/2 11:07
     * @param id
     */
    @Override
    public void pause(Integer id) {
        QuartzJob quartzJob = baseMapper.selectById(id) ;
        if (!Objects.isNull(quartzJob)){
            quartzJob.setState(JobState.JOB_STOP.getState());
            if (baseMapper.updateById(quartzJob)>0){
                quartzManage.checkStop(quartzJob);
            }
        }
    }

    /**
     * 恢复任务
     * @author YangJian
     * @date 2024/1/2 11:08
     * @param id
     */
    @Override
    public void resume(Integer id) {
        QuartzJob quartzJob = baseMapper.selectById(id) ;
        if (!Objects.isNull(quartzJob)){
            quartzJob.setState(JobState.JOB_RUN.getState());
            if (baseMapper.updateById(quartzJob)>0){
                quartzManage.resumeJob(id);
            }
        }
    }

    /**
     * 执行一次
     * @author YangJian
     * @date 2024/1/2 11:09
     * @param id
     */
    @Override
    public void runOnce(Integer id) {
        QuartzJob quartzJob = baseMapper.selectById(id) ;
        if (!Objects.isNull(quartzJob) && quartzJob.getState() != JobState.JOB_DEL.getState()){
            quartzManage.run(quartzJob);
        }
    }
}

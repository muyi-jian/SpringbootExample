package com.yj.quart.controller;

import com.yj.quart.entity.QuartzJob;
import com.yj.quart.service.QuartzJobService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangJian
 * @date 2024/1/1 17:47
 * @description
 */
@RestController
@RequestMapping("/quartz")
public class QuartzJobController {

    @Resource
    private QuartzJobService quartzJobService ;

    /**
     * 任务查询
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param id
     * @return QuartzJob
     */
    @GetMapping("/job/{id}")
    public QuartzJob getById(@PathVariable Integer id){
        return quartzJobService.getById(id) ;
    }

    /**
     * 任务新增
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param quartzJob
     * @return Integer
     */
    @PostMapping("/job")
    public Integer insert(@RequestBody QuartzJob quartzJob){
        System.out.println("job---insert");
        return quartzJobService.insert(quartzJob) ;
    }

    /**
     * 更新任务
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param quartzJob
     * @return Integer
     */
    @PutMapping("/job")
    public Integer update(@RequestBody QuartzJob quartzJob){
        return quartzJobService.update(quartzJob) ;
    }

    /**
     * 停止任务
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param id
     */
    @PutMapping("/job/pause/{id}")
    public void pause(@PathVariable("id") Integer id) {
        quartzJobService.pause(id);
    }

    /**
     * 恢复任务
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param id
     */
    @PutMapping("/job/resume/{id}")
    public void resume(@PathVariable("id") Integer id) {
        quartzJobService.resume(id) ;
    }

    /**
     * 执行一次
     * @author YangJian
     * @date 2024/1/2 11:00
     * @param id

     */
    @GetMapping("/job/runOnce/{id}")
    public void runOnce(@PathVariable("id") Integer id) {
        quartzJobService.runOnce(id) ;
    }
}

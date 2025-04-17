package com.yj.springboot3redis.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyService
 * @create 2025/4/17 10:42
 * @description <TODO description class purpose>
 */
@Service
public class MyService {
    @Cacheable(value = "my_cache") //将方法结果缓存到"my_cache"中，key为方法参数的哈希值
    public String getSomeData(String id) throws InterruptedException {
        // 模拟一个耗时操作，比如从数据库中获取数据
        Thread.sleep(1000); //休眠1秒，模拟耗时操作
        return "data for " + id;
    }
}

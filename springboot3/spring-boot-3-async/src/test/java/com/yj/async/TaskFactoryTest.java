package com.yj.async;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/2/22 20:13
 */

import com.yj.async.task.TaskFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 * 测试任务
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-29 10:49
 */
@Slf4j
@SpringBootTest
public class TaskFactoryTest {
    @Autowired
    private TaskFactory task;

    /**
     * 测试异步任务
     */
    @Test
    public void asyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<Boolean> asyncTask1 = task.asyncTask1();
        Future<Boolean> asyncTask2 = task.asyncTask2();
        Future<Boolean> asyncTask3 = task.asyncTask3();

        // 调用 get() 阻塞主线程
        asyncTask1.get();
        asyncTask2.get();
        asyncTask3.get();
        long end = System.currentTimeMillis();

        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }

    /**
     * 测试同步任务
     */
    @Test
    public void taskTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }
}

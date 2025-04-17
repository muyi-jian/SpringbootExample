package com.yj.springboot3redis;

import com.yj.springboot3redis.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class SpringBoot3RedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MyService myService;

    @Test
    public void getData() throws Exception {
        StopWatch stopWatch = new StopWatch("getData");
        stopWatch.start("1");
        System.out.println(myService.getSomeData("k"));
        stopWatch.stop();
        stopWatch.start("2");
        System.out.println(myService.getSomeData("k"));
        stopWatch.stop();
        stopWatch.start("3");
        System.out.println(myService.getSomeData("k"));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}

package com.yj.ehcache;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot3CacheEhcacheApplicationTests {

    @Test
    void contextLoads() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println("==========="+tmpDir);
    }

}

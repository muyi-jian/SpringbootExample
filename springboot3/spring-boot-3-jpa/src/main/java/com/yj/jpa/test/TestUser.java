package com.yj.jpa.test;

import com.yj.jpa.entity.User;
import com.yj.jpa.repository.UserDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/19 20:46
 */
@SpringBootTest
@Slf4j
public class TestUser {

    @Resource
    private UserDao userDao;


    /**
     * 测试单条查询
     */
    @Test
    public void testSelectOne() {
        Optional<User> user = userDao.findById(2);
        log.info("user==={}",user);
    }
}

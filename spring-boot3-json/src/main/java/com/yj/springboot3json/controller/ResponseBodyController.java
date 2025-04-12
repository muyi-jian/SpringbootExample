package com.yj.springboot3json.controller;


import com.yj.springboot3json.entity.Order;
import com.yj.springboot3json.entity.User;
import com.yj.springboot3json.entity.UserXml;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title ResponseBodyController
 * @create 2025/4/12 21:18
 * @description <TODO description class purpose>
 */
@Slf4j
@RestController
@Validated
public class ResponseBodyController {

    /**
     *  强制返回JSON格式数据
     * @author YangJian
     * @date 2025/4/12 21:54
     * @param userId  id,位數在5-8位
     * @return com.yj.springboot3json.entity.User
     */
    @GetMapping(value = "/user/json/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    // @GetMapping(value = "/user/json/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getJsonUserInfo(@PathVariable("userId") @Size(min = 5, max = 8) String userId) {
        User user = new User("FCH社区", 18);

        user.setId(Long.valueOf(userId));
        log.info("user info: {}", user);
        return user;
    }


    /**
     * 返回XML数据
     * @author YangJian
     * @date 2025/4/12 22:20
     * @param userId id
     * @return com.yj.springboot3json.entity.UserXml
     */
    @GetMapping(value = "/user/xml/{userId}", produces = MediaType.APPLICATION_XML_VALUE)
    public UserXml getXmlUserInfo(@PathVariable("userId") String userId) {
        UserXml user = new UserXml();
        user.setName("FCH");
        user.setId(userId);

        List<Order> orderList = new ArrayList<>();
        Order orderInfo1 = new Order("1001", "鸡肉", 100.0);
        Order orderInfo2 = new Order("1002", "猪肉", 200.0);
        Order orderInfo3 = new Order("1003", "牛肉", 300.0);
        orderList.add(orderInfo1);
        orderList.add(orderInfo2);
        orderList.add(orderInfo3);
        user.setOrderList(orderList);

        return user;
    }
    /**
     * JSON转换
     * @author YangJian
     * @date 2025/4/12 22:36
     * @param user user数据
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping(value = "/user/save")
    public ResponseEntity saveUser(@RequestBody @Validated User user) {
        user.setId(RandomUtils.nextLong());
        return new ResponseEntity(user, HttpStatus.OK);
    }

}

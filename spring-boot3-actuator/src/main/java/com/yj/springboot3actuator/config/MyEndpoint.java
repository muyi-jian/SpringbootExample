package com.yj.springboot3actuator.config;


import com.yj.springboot3actuator.entity.User;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyEndpoint
 * @create 2025/4/15 16:00
 * @description <TODO description class purpose>
 */
@Component
@Endpoint(id = "test")
public class MyEndpoint {

    @ReadOperation
    public User getUser(@Selector Integer id) {
        return new User(id, "tom", 30);
    }

    @WriteOperation
    public User updateUser(int id, @Nullable String name, @Nullable Integer age) {
        User user = getUser(id);
        user.setName(user.getName());
        user.setAge(user.getAge());
        return user;
    }
}

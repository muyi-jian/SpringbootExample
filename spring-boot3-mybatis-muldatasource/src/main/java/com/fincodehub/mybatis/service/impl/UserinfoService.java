package com.fincodehub.mybatis.service.impl;


import com.fincodehub.mybatis.entity.Userinfo;
import com.fincodehub.mybatis.mapper.test2.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserService
 * @create 2025/5/12 10:19
 * @description <TODO description class purpose>
 */
@Service
public class UserinfoService implements com.fincodehub.mybatis.service.UserinfoService {
    @Autowired
    UserinfoMapper userinfoMapper;
    @Override
    public Userinfo getUserById(Integer id) {
        System.out.println("test2:::::::::"+id);
        Userinfo userById = userinfoMapper.getUserById(id);
        return userById;
    }
}

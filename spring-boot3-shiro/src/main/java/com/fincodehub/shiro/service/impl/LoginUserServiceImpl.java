package com.fincodehub.shiro.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fincodehub.shiro.entity.User;
import com.fincodehub.shiro.mapper.LoginUserMapper;
import com.fincodehub.shiro.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserServiceImpl
 * @create 2025/5/24 23:07
 * @description <TODO description class purpose>
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserMapper userMapper;
    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> queryWapper = new QueryWrapper<>();
        queryWapper.eq("name",name);
        return userMapper.selectOne(queryWapper);
    }


    /**
     * 获取用户角色信息
     * @author YangJian
     * @date 2025/5/24 20:38
     * @param principal
     * @return java.util.List<java.lang.String>
     */
    @Override
    public List<String> getUserRoleInfo(String principal) {
        System.out.println("当前用户："+principal);
        List<String> userRoleInfoMapper = userMapper.getUserRoleInfoMapper(principal);
        for (String s : userRoleInfoMapper) {
            System.out.println("当前用户角色信息："+s);
        }
        return userRoleInfoMapper;
    }

    @Override
    public List<String> getUserPermissionInfoMapper(List<String> roles) {
        return userMapper.getUserPermissionInfoMapper(roles);
    }
}

package com.yj.springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.springboot3.entity.User;
import com.yj.springboot3.mapper.UserMapper;
import com.yj.springboot3.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author YangJian
 * @date 2024/1/1 11:23
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(null);
    }

    @Override
   public Integer addUser(User user){
        return baseMapper.insert(user);
    }

    @Override
    public IPage<User> getUserByPage(Page<User> pageParam, String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println("name======="+name);
        if (StringUtils.hasText(name)){
            System.out.println("name1======="+name);
            queryWrapper.like("name",name);
        }
        return baseMapper.selectPage(pageParam, queryWrapper);
    }
}

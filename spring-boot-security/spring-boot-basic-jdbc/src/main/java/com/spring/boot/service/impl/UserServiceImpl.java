package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.entity.Perm;
import com.spring.boot.entity.User;
import com.spring.boot.mapper.PermMapper;
import com.spring.boot.mapper.UserMapper;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermMapper permMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);

        User user = userMapper.selectOne(queryWrapper);

        if (user == null){
            throw new UsernameNotFoundException("用户未找到");
        }
        // 根据用户名查找权限
        QueryWrapper<Perm> permQueryWrapper = new QueryWrapper<>();
        permQueryWrapper.eq("user_id",user.getId());

        List<Perm> perms = permMapper.selectList(permQueryWrapper);

        // 权限标识
        List<String> permTags = perms.stream().map(Perm::getTag).collect(Collectors.toList());
        user.setAuthorities(AuthorityUtils.createAuthorityList(permTags));
        System.out.println("user==="+user.toString());
        return user;
    }
}

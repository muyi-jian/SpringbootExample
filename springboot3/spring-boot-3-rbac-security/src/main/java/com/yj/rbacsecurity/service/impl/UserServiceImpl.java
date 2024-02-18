package com.yj.rbacsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.rbacsecurity.entity.User;
import com.yj.rbacsecurity.mapper.UserMapper;
import com.yj.rbacsecurity.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("user======="+user);

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin:api");

        user.setAuthorities(grantedAuthorities);
        System.out.println("user1======="+user);

        return user;
    }
}

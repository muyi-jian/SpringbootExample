package com.spring.jwt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.jwt.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {
}

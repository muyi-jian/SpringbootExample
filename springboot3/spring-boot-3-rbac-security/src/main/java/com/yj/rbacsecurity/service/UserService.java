package com.yj.rbacsecurity.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.rbacsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {
}

package com.yj.springboot3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.springboot3.entity.User;

import java.util.List;

/**
 * @author YangJian
 * @date 2024/1/1 11:23
 * @description
 */
public interface UserService extends IService<User> {
   List<User> getUserList();

   Integer addUser(User user);

   IPage<User> getUserByPage(Page<User> pageParam, String name);
}

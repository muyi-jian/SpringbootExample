package com.yj.jdbctemplate.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.yj.jdbctemplate.constant.Constant;
import com.yj.jdbctemplate.dao.UserDao;
import com.yj.jdbctemplate.entity.User;
import com.yj.jdbctemplate.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/18 21:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public int insertUser(User user) {
        String rawPass = user.getPassword();
        String salt = IdUtil.simpleUUID();
        String pass = SecureUtil.md5(rawPass + Constant.SALT_PREFIX + salt);
        user.setPassword(pass);
        user.setSalt(salt);
        //系统字段
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setStatus(1);
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}

package com.fincodehub.mybatis.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fincodehub.mybatis.entity.LoginUser;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserService
 * @create 2025/4/29 16:40
 * @description <TODO description class purpose>
 */
public interface LoginUserService extends IService<LoginUser> {
    LoginUser findById(Integer id);

    boolean saveData(LoginUser loginUser);

    IPage<LoginUser> getPageData(int pagenum, int pageSize);

    Page<LoginUser> selectPage(Page<LoginUser> page, Object o);
}

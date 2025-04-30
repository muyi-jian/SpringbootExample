package com.fincodehub.mybatis.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fincodehub.mybatis.entity.LoginUser;
import com.fincodehub.mybatis.mapper.LoginUserMapper;
import com.fincodehub.mybatis.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserServiceImpl
 * @create 2025/4/29 16:44
 * @description <TODO description class purpose>
 */
@Service
@Slf4j
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements LoginUserService {

    @Autowired
    LoginUserMapper loginUserMapper;
    @Override
    public LoginUser findById(Integer id) {
        return getById(id);
    }
    @Override
    public boolean saveData(LoginUser loginUser) {
        return save(loginUser);
    }

    @Override
    public IPage<LoginUser> getPageData(int pageNum, int pageSize) {
        Page<LoginUser> page = Page.of(pageNum,pageSize);
        QueryWrapper<LoginUser> queryWrapper = new QueryWrapper<>();

        IPage<LoginUser> loginUserIPage = loginUserMapper.selectPage(page, queryWrapper);
        log.info("Total recoder" + loginUserIPage.getTotal());
        log.info("Total Pages" + loginUserIPage.getPages());
        return loginUserIPage;
    }

    @Override
    public Page<LoginUser> selectPage(Page<LoginUser> page, Object o) {
        return loginUserMapper.selectPage(page, null);
    }


}

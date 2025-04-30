package com.fincodehub.mybatis.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.mybatis.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserMapper
 * @create 2025/4/29 16:40
 * @description <TODO description class purpose>
 */
@Mapper
public interface LoginUserMapper extends BaseMapper<LoginUser> {
    //IPage<LoginUser> selectPageVo(IPage<?> page, Integer state);
    // 或者自定义分页类
    //MyPage selectPageVo(MyPage page);
    // 或者返回 List
    //List<LoginUser> selectPageVo(IPage<LoginUser> page, Integer state);
}

package com.fincodehub.mybatis.mapper;


import com.fincodehub.mybatis.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserMapper
 * @create 2025/4/29 15:54
 * @description <TODO description class purpose>
 */
@Mapper
public interface LoginUserMapper {
    LoginUser getUserById(@Param("id") Integer id);
}

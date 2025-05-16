package com.fincodehub.mybatis.mapper.test2;


import com.fincodehub.mybatis.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserMapper
 * @create 2025/5/12 10:03
 * @description <TODO description class purpose>
 */
@Mapper
public interface UserinfoMapper {
    Userinfo getUserById(@Param("id") Integer id);

}

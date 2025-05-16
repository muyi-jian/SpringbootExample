package com.fincodehub.mybatis.mapper.test1;


import com.fincodehub.mybatis.entity.User;
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
public interface UserMapper {
    User getUserById(@Param("id") Integer id);
}

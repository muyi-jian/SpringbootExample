package com.spring.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.jwt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

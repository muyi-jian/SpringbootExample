package com.yj.rbacsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.rbacsecurity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

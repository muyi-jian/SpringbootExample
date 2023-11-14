package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.entity.Perm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermMapper extends BaseMapper<Perm> {
}

package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.entity.SysToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysTokenMapper extends BaseMapper<SysToken> {
}

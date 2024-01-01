package com.yj.springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.springboot3.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author YangJian
 * @date 2023/11/28 21:55
 * @description
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}

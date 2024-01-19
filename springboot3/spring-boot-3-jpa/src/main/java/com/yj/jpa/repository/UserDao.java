package com.yj.jpa.repository;

import com.yj.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/19 20:43
 */

public interface UserDao extends JpaRepository<User, Integer> {//实体类名称和主键类型

}

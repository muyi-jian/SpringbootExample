package com.yj.springboot3web.repository;


import com.yj.springboot3web.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserRepository
 * @create 2025/4/16 9:38
 * @description <TODO description class purpose>
 */

public interface UserRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findUserByUsername(String username);
}

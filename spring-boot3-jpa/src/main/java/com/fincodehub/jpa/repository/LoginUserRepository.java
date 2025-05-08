package com.fincodehub.jpa.repository;


import com.fincodehub.jpa.entity.LoginUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserRepository
 * @create 2025/5/8 16:47
 * @description <TODO description class purpose>
 */
public interface LoginUserRepository extends CrudRepository<LoginUser, Integer> {
    
    LoginUser findLoginUserById(Integer id);
}

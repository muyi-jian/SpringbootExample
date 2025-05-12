package com.fincodehub.jpa.repository;


import com.fincodehub.jpa.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserRepository
 * @create 2025/5/8 16:47
 * @description <TODO description class purpose>
 */
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>,
        PagingAndSortingRepository<LoginUser, Integer>, JpaSpecificationExecutor<LoginUser> {
    
    LoginUser findLoginUserById(Integer id);
    // 查询
    @Query("SELECT u FROM LoginUser u WHERE u.username = ?1")
    LoginUser findByName(String a2);

    // List<LoginUser> findAll(PageRequest pageRequest);
}

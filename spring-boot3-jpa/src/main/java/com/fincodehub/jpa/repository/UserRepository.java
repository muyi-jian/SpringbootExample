package com.fincodehub.jpa.repository;


import com.fincodehub.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserRepository
 * @create 2025/5/8 21:24
 * @description <TODO description class purpose>
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email  LIKE %:keyword%")
    List<User> findByEmailLike(@Param("keyword") String keyword);

}

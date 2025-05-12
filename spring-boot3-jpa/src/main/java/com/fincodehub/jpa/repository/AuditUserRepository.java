package com.fincodehub.jpa.repository;


import com.fincodehub.jpa.entity.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YangJian
 * @version 1.0.0
 * @title AuditUserRepository
 * @create 2025/5/9 8:37
 * @description <TODO description class purpose>
 */
@Repository
public interface AuditUserRepository extends JpaRepository<AuditUser, Long> {
    AuditUser findByName(String name);
}

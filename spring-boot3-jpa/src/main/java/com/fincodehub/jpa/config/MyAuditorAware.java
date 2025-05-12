package com.fincodehub.jpa.config;


import com.fincodehub.jpa.entity.AuditUser;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyAuditorAware
 * @create 2025/5/9 9:24
 * @description <TODO description class purpose>
 */
public class MyAuditorAware implements AuditorAware<AuditUser> {
    private Optional<AuditUser> currentUser = Optional.empty();

    public void setCurrentUser(AuditUser currentUser){
        this.currentUser = Optional.of(currentUser);
    }

    @Override
    public Optional<AuditUser> getCurrentAuditor() {
        //要使用的当前用户
        return currentUser;
    }
}

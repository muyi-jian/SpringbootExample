package com.fincodehub.jpa.config;


import com.fincodehub.jpa.entity.LoginUser;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author YangJian
 * @version 1.0.0
 * @title CustomAuditor
 * @create 2025/5/9 9:28
 * @description <TODO description class purpose>
 */
public class CustomAuditor implements AuditorAware<Integer> {
    private Optional<Integer> currentUser = Optional.empty();

    public void setCurrentUser(LoginUser loginUser){
        this.currentUser = Optional.of(loginUser.getId());
    }
    @Override
    public Optional<Integer> getCurrentAuditor() {
        // 这里可以添加自定义逻辑来返回当前的审计人员，比如从当前登录用户中获取
        return currentUser;
    }
}

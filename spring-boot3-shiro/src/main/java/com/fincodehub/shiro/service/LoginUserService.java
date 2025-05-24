package com.fincodehub.shiro.service;


import com.fincodehub.shiro.entity.User;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserService
 * @create 2025/5/24 23:06
 * @description <TODO description class purpose>
 */
public interface LoginUserService {
    User getUserInfoByName(String name);
    List<String> getUserRoleInfo(String principal);
    List<String> getUserPermissionInfoMapper(List<String>roles);
}

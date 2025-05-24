package com.fincodehub.shiro.realm;


import com.fincodehub.shiro.entity.User;
import com.fincodehub.shiro.service.LoginUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyRealm
 * @create 2025/5/24 23:11
 * @description <TODO description class purpose>
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private LoginUserService loginUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("进入自定义授权方法");
        // 创建对象，存储当前登录的用户的权限和角色
        // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 存储角色
        // info.addRole("admin");
        // 返回授权信息
        // 获取当前用户身份信息
        String primaryPrincipal = principalCollection.getPrimaryPrincipal().toString();
        // 调用接口方法获取用户的人角色信息
        List<String> roles = loginUserService.getUserRoleInfo(primaryPrincipal);
        System.out.println("当前用户角色信息："+roles);
        // 获取用户权限信息
        List<String> permissions = loginUserService.getUserPermissionInfoMapper(roles);
        System.out.println("当前用户权限信息："+permissions);
        //创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 存储角色
        info.addRoles(roles);
        // 存储权限
        info.addStringPermissions(permissions);
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String rincipal = authenticationToken.getPrincipal().toString();
        User user = loginUserService.getUserInfoByName(rincipal);
        if (user != null){
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes(user.getSalt()),
                    authenticationToken.getPrincipal().toString());
            return info;
        }
        return null;
    }
}

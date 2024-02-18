package com.yj.rbacsecurity.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * {@Link Authentication}及其实现类的大部分属性没有提供setter 方法,所以在通过json转换回Athentidation时，
 * 没有setter 方法的属性就赋值为空，此类json转换Authentication的中间类
 */
public class JwtAuthentication implements Authentication {

    private Collection<SimpleGrantedAuthority> authorities;

    private Object details;
    private boolean authenticated;
    private Object principal;
    private Object credentaials;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Object getCredentials() {
        return credentaials;
    }

    public void setCredentaials(Object credentaials) {
        this.credentaials = credentaials;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}

package com.spring.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@TableName("t_user")
@Data
public class User implements UserDetails {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("enabled")
    private Boolean enabled;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.fincodehub.jdbctemplate.service.impl;


import com.fincodehub.jdbctemplate.entity.LoginUser;
import com.fincodehub.jdbctemplate.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserServiceImpl
 * @create 2025/5/7 21:47
 * @description <TODO description class purpose>
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LoginUser>  queryLoginUser() {
        List<LoginUser> loginUser = jdbcTemplate.query("SELECT * FROM login_user", (rs, rowNum) ->
        new LoginUser(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                rs.getString("nick_name")));
        return loginUser;
    }

    public void save(LoginUser user) {
        jdbcTemplate
                .update("INSERT INTO login_user(id,username, password,nick_name) VALUES (?, ?, ?, ?)",
                        user.getId(),user.getUsername(), user.getPassword(),  user.getNickName());
    }

    public void update(LoginUser user) {
        jdbcTemplate
                .update("UPDATE login_user SET username = ?, password = ? WHERE id = ?",
                        user.getUsername(), user.getPassword(), user.getId());
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM login_user WHERE id = ?", id);
    }
}

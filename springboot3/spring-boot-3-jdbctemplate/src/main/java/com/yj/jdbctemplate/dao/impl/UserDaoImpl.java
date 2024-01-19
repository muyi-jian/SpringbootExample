package com.yj.jdbctemplate.dao.impl;

import com.yj.jdbctemplate.dao.UserDao;
import com.yj.jdbctemplate.entity.User;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/18 21:13
 */
@Repository
public class UserDaoImpl implements UserDao {

    //导入JDBCTemplate模板
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertUser(User user) {
        return jdbcTemplate.update("INSERT INTO user(name,password,salt,email,phone_number) VALUES ( ?, ?, ?, ?, ?)",
                user.getName(),user.getPassword(),user.getSalt(),user.getEmail(),user.getPhoneNumber());

    }

    @Override
    public int updateUser(User user) {

        return jdbcTemplate.update("UPDATE user set name=?,email=?,phone_number=? where id=?",
                user.getName(),user.getEmail(),user.getPhoneNumber(),user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        return jdbcTemplate.update("DELETE FROM user where id=?",id);
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM USER";
        final List<User> list=new ArrayList<>();

         jdbcTemplate.query(sql, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                User temp=new User();
                temp.setId(rs.getLong("id"));
                temp.setName(rs.getString("name"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phone_number"));
                temp.setCreateTime(rs.getDate("create_time"));
                temp.setLastUpdateTime(rs.getDate("last_update_time"));
                temp.setLastLoginTime(rs.getDate("last_login_time"));
                list.add(temp);

            }
        });
         return list;
    }
}

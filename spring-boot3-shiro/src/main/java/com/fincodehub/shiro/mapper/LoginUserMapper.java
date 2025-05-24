package com.fincodehub.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.shiro.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title loginUserMapper
 * @create 2025/5/24 23:04
 * @description <TODO description class purpose>
 */
@Repository
public interface LoginUserMapper extends BaseMapper<User> {

    @Select("SELECT NAME FROM role WHERE id IN (SELECT role_id FROM role_user WHERE user_id=(SELECT id FROM USER WHERE NAME=#{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);

    @Select({
            "<script>",
            "select info FROM permissions WHERE id IN ",
            "(SELECT permission_id FROM role_ps WHERE role_id IN (",
            "SELECT id FROM role WHERE NAME IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
    })
    List<String> getUserPermissionInfoMapper(@Param("roles") List<String>roles);
}

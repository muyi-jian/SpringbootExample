package spring.boot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import spring.boot.mybatisplus.entity.User;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}

package spring.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring.boot.entity.User;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 16:50
 */

@Mapper
public interface UserMapper {
    List<User> getList();
    List<User> getList2();
}

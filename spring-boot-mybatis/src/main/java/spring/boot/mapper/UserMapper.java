package spring.boot.mapper;

import org.springframework.stereotype.Repository;
import spring.boot.entity.User;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 16:50
 */

@Repository
public interface UserMapper {
    List<User> getList();
    List<User> getList2();
}

package spring.boot.service;

import spring.boot.entity.User;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 17:17
 */
public interface UserService {
    List<User> getList();
    List<User> getList2();
}

package spring.boot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import spring.boot.mybatisplus.entity.User;

public interface UserService  extends IService<User> {
    User selectByUsername(String username);
}

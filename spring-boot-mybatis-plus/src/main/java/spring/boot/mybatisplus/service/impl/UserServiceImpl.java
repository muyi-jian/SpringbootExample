package spring.boot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.mybatisplus.entity.User;
import spring.boot.mybatisplus.mapper.UserMapper;
import spring.boot.mybatisplus.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }


}

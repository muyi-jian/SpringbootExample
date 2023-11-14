package spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.config.DataSource;
import spring.boot.config.DataSourceType;
import spring.boot.entity.User;
import spring.boot.mapper.UserMapper;
import spring.boot.service.UserService;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 17:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getList() {
        return userMapper.getList();
    }

    /**
     * 配置访问从库
     * @return
     */
    @Override
    @DataSource(DataSourceType.SLAVE)
    public List<User> getList2() {
        return userMapper.getList2();
    }
}

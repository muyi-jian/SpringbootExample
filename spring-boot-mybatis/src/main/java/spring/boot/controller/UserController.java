package spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.entity.User;
import spring.boot.service.UserService;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 17:22
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> getList(){
        return userService.getList();
    }

    @GetMapping("/get2")
    public List<User> getList2(){
        return userService.getList2();
    }
}

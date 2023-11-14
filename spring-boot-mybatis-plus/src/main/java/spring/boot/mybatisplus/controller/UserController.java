package spring.boot.mybatisplus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mybatisplus.entity.User;
import spring.boot.mybatisplus.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     *
     * @return
     */
    @GetMapping("/user/info")
    public User selectByUsername(@RequestParam("username") String username){
        System.out.println("usrname::::"+username);
        return userService.selectByUsername(username);

    }
}

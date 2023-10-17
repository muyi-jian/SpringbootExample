package com.spring.boot.controller;

import com.spring.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping("/index")
    public String index(Model model){

        List<Object> allUser = sessionRegistry.getAllPrincipals();
        model.addAttribute("users", allUser);

        return "index";
    }
    @GetMapping("/kickout")
    public String kickout(String username){
        System.out.println("username==="+username);
        List<Object> allUsers = sessionRegistry.getAllPrincipals();
        for (Object allUser : allUsers){
            System.out.println("allUser==="+allUser);

            //获取所有已经登录并且未失效的session
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(allUser, false);
            User user = (User) allUser;
            if (user.getUsername().equals(username)){
                // 将所有已经登录的session会话都给失效
                System.out.println("失效==="+username);
                allSessions.forEach(SessionInformation::expireNow);
            }
        }
        return "redirect:/index";
    }


    @GetMapping("/admin/api")
    public String admin(){
        return "admin";
    }
    @GetMapping("/user/api")
    public String user(){
        return "user";
    }
    @GetMapping("/app/api")
    public String app(){
        return "app";
    }
    @GetMapping("noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @GetMapping("/test/a")
    public String testa(){
        return "adminA";
    }
    @GetMapping("/test/api/a/b")
    public String testab(){
        return "adminAB";
    }
    @GetMapping("/test/a/b/c/d")
    public String testabcd(){
        return "adminABCD";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/app/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/app/invalidSession")
    public String invalidSession(){
        return "invalidSession";
    }

}

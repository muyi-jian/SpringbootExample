package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.boot.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/23 8:48
 */
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model m) {
        List<UserInfo> list = new ArrayList<UserInfo>();
        list.add(new UserInfo("KangKang", "康康", 20));
        list.add(new UserInfo("Mike", "麦克", 22));
        list.add(new UserInfo("Jane","简",23));
        list.add(new UserInfo("Maria", "玛利亚", 25));
        m.addAttribute("userInfo",list);
        return "test";
    }
}

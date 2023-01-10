package spring.boot.actuator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/12/28 9:36
 */
@RestController
public class TestController {

    @RequestMapping("/")
    String index() {
        return "hello spring boot";
    }
}

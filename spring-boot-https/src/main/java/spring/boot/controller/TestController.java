package spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/11/25 10:41
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}

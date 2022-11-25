package spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/11/23 20:13
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public String testDevtools(){
        log.info("演示修改bug1");
        return "ok";
    }
}

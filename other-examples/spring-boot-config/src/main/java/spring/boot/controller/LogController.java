package spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/11/22 20:19
 */
@RestController
public class LogController {
    private Logger log = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/testLog")
    public String testLog(String msg) {
        log.info("这是info信息：{}", msg);
        log.debug("这是debug信息：{}", msg);
        log.warn("这是warn信息：{}", msg);
        log.error("这是error信息：{}", msg);
        return "success";
    }
}


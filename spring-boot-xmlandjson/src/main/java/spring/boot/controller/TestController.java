package spring.boot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjian
 * @date 2022/11/25 11:38
 */
@RestController
public class TestController {

    /**
     * MediaType 类型：
     * APPLICATION_JSON_VALUE = "application/json"
     * APPLICATION_XML_VALUE = "application/xml"
     * @return
     */
    @GetMapping(value = "/test",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Object test(){
        Map<String,String> map = new HashMap<>();
        map.put("code1","1");
        map.put("code2","2");
        map.put("code3","3");
        return map;
    }
    @GetMapping(value = "/test1",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object test1(){
        Map<String,String> map = new HashMap<>();
        map.put("code1","1");
        map.put("code2","2");
        map.put("code3","3");
        return map;
    }
    @GetMapping(value = "/test2",produces = {MediaType.APPLICATION_XML_VALUE})
    public Object test2(){
        Map<String,String> map = new HashMap<>();
        map.put("code1","1");
        map.put("code2","2");
        map.put("code3","3");
        return map;
    }

}

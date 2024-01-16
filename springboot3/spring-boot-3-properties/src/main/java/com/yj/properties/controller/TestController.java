/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:48
 * @version 1.0.0
 */
package com.yj.properties.controller;

import com.yj.properties.entity.ApplicationProperty;
import com.yj.properties.entity.DeveloperProperty;
import com.yj.properties.entity.DeveloperProperty2;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:48
 * @version 1.0.0
 */
@RestController
public class TestController {

    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;
    @Resource
    private  DeveloperProperty2 developerPropert2;
    @Autowired
    public TestController(ApplicationProperty applicationProperty,
                          DeveloperProperty developerProperty
    ) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
    }

    @Autowired
    public Environment environment;

    @GetMapping({"/","/property"})
    public Map<String,Object> index() {
        Map<String,Object> map = new HashMap<>();
        map.put("applicationProperty",applicationProperty);
        map.put("developerProperty", developerProperty);
        map.put("developerProperty2", developerPropert2);
        map.put("environment",environment.getProperty("developer.name"));
        return map;
    }


}

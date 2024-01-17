/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:37
 * @version 1.0.0
 */
package com.yj.exception.controller;

import com.yj.exception.constant.Status;
import com.yj.exception.ex.JsonException;
import com.yj.exception.ex.PageException;
import com.yj.exception.model.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:37
 */
@Controller
public class TestController {
    @GetMapping("/testOk")
    @ResponseBody
    public ApiResponse testOk() {
        // return ApiResponse.ofSuccess("1111111111111");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return ApiResponse.ofSuccess(map);
    }

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }


}

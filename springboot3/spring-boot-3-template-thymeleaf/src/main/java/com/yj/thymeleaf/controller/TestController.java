package com.yj.thymeleaf.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 21:14
 * @version 1.0.0
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/page/index");
        mv.addObject("name", "yangjian");
        mv.addObject("b", true);


        return mv;
    }
}

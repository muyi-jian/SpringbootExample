/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 21:14
 * @version 1.0.0
 */
package com.yj.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 21:14
 * @version 1.0.0
 */
@Controller
public class TestController {

    @GetMapping({"","/","/index"})
    public ModelAndView  index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("name", "yangjian");
        mv.addObject("b", true);
        return mv;
    }
}

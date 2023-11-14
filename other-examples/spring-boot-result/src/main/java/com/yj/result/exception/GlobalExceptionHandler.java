package com.yj.result.exception;

import com.yj.result.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一处理异常
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody + ..
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultUtil<Object> handAllExceptions(Exception e){
        System.out.println("handAllExceptions====================");

        return  ResultUtil.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        System.out.println("myErrorHandler====================");
        ModelAndView modelAndView = new ModelAndView();
        //指定错误页面的模板页
        // 视图名称
        modelAndView.setViewName("error");


        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }
}

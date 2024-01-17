/**
 * 统一异常处理
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:33
 * @version 1.0.0
 */
package com.yj.exception.handler;

import com.yj.exception.ex.JsonException;
import com.yj.exception.ex.PageException;
import com.yj.exception.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:33
 * @version 1.0.0
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 统一json 异常处理
     * @param exception JsonException
     * @date  2024/1/17 20:35
     * @return ApiResponse 统一返回格式
     */
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException exception) {
        log.error("[JsonException]:{}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }
    /**
     * 统一 页面 异常处理
     *
     * @param exception PageException
     * @date  2024/1/17 20:35
     * @return 统一跳转到异常页面
     */
    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException exception) {
        log.error("[PageException]:{}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

}

package com.fincodehub.web.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyInterceptor
 * @create 2025/4/28 20:46
 * @description <TODO description class purpose>
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * ⽅法执⾏前执⾏
     * preHandle方法内部代码可以执行以下操作：
     *   1. 首先检查HTTP请求头中是否包含有效的token
     *   2. 如果token存在，则通过用户身份校验，请求可以继续处理；
     *   3. 如果token不存在，方法会构建一个错误信息响应，以JSON格式返回给客户端，并终止请求继续向下执行。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("⽬标⽅法执⾏前执⾏");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("⽬标⽅法执⾏中执⾏");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("⽬标⽅法执⾏后执⾏");
    }
}

package com.yj.result.config;

import com.alibaba.fastjson.JSON;
import com.yj.result.annotaion.IgnoreResponseHandler;
import com.yj.result.utils.ResultUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一处理返回数据格式
 */
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("supports====================");
        // 忽略不使用特殊定义返回数据格式的
        return !returnType.hasMethodAnnotation(IgnoreResponseHandler.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        System.out.println("beforeBodyWrite====================");
        ResultUtil rt = null;
        if (body instanceof ResultUtil) {
            rt = (ResultUtil) body;
        }else {
            rt = new ResultUtil();
            rt.setData(body);
        }
        //如果返回的字符串类型，会先判断HttpMessageConverter能否支持对应的返回类型再使用ResponseBodyAdvice进行封装
        //那么此时在进来就不是String类型，所以会报无法转换成ResponseVO对象,那么这里有两种方法，一种是直接返回json字符串，另一种是
        //一种是自己的WebConfig进行额外的配置
        if (body instanceof String){
            return JSON.toJSONString(rt);
        }
        return rt;
    }
}

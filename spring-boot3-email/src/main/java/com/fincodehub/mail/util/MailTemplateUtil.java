package com.fincodehub.mail.util;


import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MailTemplateUtil
 * @create 2025/4/30 14:52
 * @description <TODO description class purpose>
 */
@Component
public class MailTemplateUtil {

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 获得验证码模板
     */
    public String getMailTemplat(String email, String code) {
        Context context = new Context();
        //设置模板所需的参数
        context.setVariable("title","验证码");
        context.setVariable("email",email);
        context.setVariable("code",code);
        context.setVariable("date", DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
        //通过模板类将动态参数传入HTML模板,并返回模板内容 参数一:模板名字，参数二：动态参数Web文本
        String content = templateEngine.process("/email-template", context);
        return content;
    }
}

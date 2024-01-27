package com.yj.email.test;

import com.yj.email.util.MailUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/27 18:54
 */
@SpringBootTest
public class MailUtilTest {
    @Resource
    private MailUtil mailUtil;

    /**
     * 测试简单邮件发送
     */
    @Test
    public void testSendSampleMail() {
        mailUtil.sendSampleMail("1475136846@qq.com", "测试发送简单邮件", "hello world.");
        System.out.println("******执行发送简单邮件成功******");
    }

    /**
     * 测试带附件邮件发送
     */
    @Test
    public void testSendAttachmentMail() throws Exception {
        mailUtil.sendAttachmentMail("1475136846@qq.com", "测试发送附件邮件", "hello world.", "ttttxz.xlsx", "D:\\temp\\tttt.xlsx");
        System.out.println("******执行发送附件邮件成功******");
    }

    /**
     * 测试模板邮件发送
     */
    @Test
    public void testSendTemplateMail() throws Exception {
        mailUtil.sendTemplateMail("1475136846@qq.com", "测试发送模板邮件",  "templates/mail_template.ftl", "展示效果1", "展示效果2");
        System.out.println("******执行发送模板邮件成功******");
    }

}

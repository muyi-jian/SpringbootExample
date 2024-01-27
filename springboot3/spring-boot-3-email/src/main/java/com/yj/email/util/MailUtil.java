package com.yj.email.util;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.MessageFormat;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/27 18:53
 */
@Component
@Slf4j
public class MailUtil {
    @Value(value = "${spring.mail.username}")
    private String from;

    private final JavaMailSender JavaMailSender;

    public MailUtil(JavaMailSender JavaMailSender) {
        this.JavaMailSender = JavaMailSender;
    }


    public void sendSampleMail(String to, String subject, String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(context);
        JavaMailSender.send(message);
    }

    public void sendAttachmentMail(String to, String subject, String context, String attachmentName, String filePath) throws Exception {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(context);

        //上传文件
        helper.addAttachment(attachmentName, new File(filePath));
        JavaMailSender.send(mimeMessage);
    }

    public void sendTemplateMail(String to, String subject, String templatePath, String... arguments) throws Exception {
        MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(this.buildTemplateContext(templatePath, arguments), true);
        JavaMailSender.send(mimeMessage);
    }

    private String buildTemplateContext(String templatePath, String... arguments) {
        //加载邮件html模板
        Resource resource = new ClassPathResource(templatePath);
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.info("读取模板失败:", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), arguments);
    }
}
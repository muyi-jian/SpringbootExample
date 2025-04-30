package com.fincodehub.mail.service.impl;


import com.fincodehub.mail.entity.MailInfo;
import com.fincodehub.mail.service.SendMail;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SendMailImpl
 * @create 2025/4/30 14:55
 * @description <TODO description class purpose>
 */
@Service
@Slf4j
public class SendMailImpl implements SendMail {

    @Resource
    JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public boolean sendEMail(MailInfo mailInfo) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from); //设置发送邮件账号
            simpleMailMessage.setTo(mailInfo.getToUser().toArray(String[]::new)); //设置接收邮件的人，可以多个
            simpleMailMessage.setSubject(mailInfo.getSubject()); //设置发送邮件的主题
            simpleMailMessage.setText(mailInfo.getContent()); //设置发送邮件的内容
            mailSender.send(simpleMailMessage);
            return true;
        } catch (MailException e) {
            log.error("邮件发送失败!",e);
        }
        return false;
    }

    public boolean sendHtmlEMail(MailInfo mailInfo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
            minehelper.setFrom(from);
            minehelper.setTo(mailInfo.getToUser().toArray(String[]::new)); //设置接收邮件的人，可以多个
            minehelper.setSubject(mailInfo.getSubject()); //设置发送邮件的主题
            minehelper.setText(mailInfo.getContent(),true); //设置发送邮件的内容 第二个设置为true则可以发送带HTML的邮件
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            log.error("邮件发送失败!",e);
        }
        return false;
    }
    public boolean sendFileEMail(MailInfo mailInfo, String filePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
            minehelper.setFrom(from);
            minehelper.setTo(mailInfo.getToUser().toArray(String[]::new)); //设置接收邮件的人，可以多个
            minehelper.setSubject(mailInfo.getSubject()); //设置发送邮件的主题
            minehelper.setText(mailInfo.getContent(),true); //设置发送邮件的内容 第二个设置为true则可以发送带HTML的邮件

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileExtendName = filePath.substring(filePath.lastIndexOf(File.separator));
            log.info("fileExtendName:{}",fileExtendName);
            log.info("filePath:{}",filePath);
            minehelper.addAttachment(fileExtendName, file);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            log.error("邮件发送失败!",e);
        }
        return false;
    }

}

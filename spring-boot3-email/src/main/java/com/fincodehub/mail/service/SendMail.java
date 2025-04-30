package com.fincodehub.mail.service;


import com.fincodehub.mail.entity.MailInfo;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SendMail
 * @create 2025/4/30 14:55
 * @description <TODO description class purpose>
 */
public interface SendMail {
    boolean sendEMail(MailInfo mailInfo);
    boolean sendHtmlEMail(MailInfo mailInfo);
    boolean sendFileEMail(MailInfo mailInfo,String filePath);
}

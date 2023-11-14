package spring.boot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mail.MailProperties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author yangjian
 * @date 2022/11/25 14:26
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final JavaMailSender javaMailSender;

    private final MailProperties mailProperties;

    @RequestMapping("/sendEmail")
    @ResponseBody
    public boolean sendEmail(@RequestParam("email") String email,
                             @RequestParam("text") String text) {
        try {
            log.info("email==="+email);
            log.info("text==="+text);
            MimeMessage msg = createMimeMsg(email, text, "logo.png");
            javaMailSender.send(msg);
        } catch (Exception ex) {
            log.error("邮件发送失败：", ex);
            return false;
        }
        return true;
    }
    @RequestMapping("/sendEmail1")
    @ResponseBody
    public boolean sendEmail1(@RequestParam("email") String email,
                             @RequestParam("text") String text) {
        try {
            log.info("email{}",email);
            log.info("tex{}",text);
            SimpleMailMessage msg = createSimpleMsg(email, text);
            javaMailSender.send(msg);
        } catch (Exception ex) {
            log.error("邮件发送失败：", ex);
            return false;
        }
        return true;
    }

    /**
     * 创建复杂邮件
     *
     * @param email
     * @param text
     * @param attachmentClassPath
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private MimeMessage createMimeMsg(String email, String text, String attachmentClassPath) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
        mimeMessageHelper.setFrom(mailProperties.getFrom(), mailProperties.getPersonal());
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setBcc(mailProperties.getBcc());
        mimeMessageHelper.setSubject(mailProperties.getSubject());
        mimeMessageHelper.setText(text);
        // 第一个参数是自定义的名称，后缀需要加上，第二个参数是文件的位置
        //mimeMessageHelper.addAttachment("资料.xlsx",new File("/Users/gamedev/Desktop/测试数据 2.xlsx"));
        //ClassPathResource 读取项目resource下的文件
        File file = new ClassPathResource(attachmentClassPath).getFile();
        log.info("filename{}",file.getName());
        mimeMessageHelper.addAttachment(file.getName(), file);
        return msg;
    }

    /**
     * 创建简单邮件
     *
     * @param email
     * @param text
     * @return
     */
    private SimpleMailMessage createSimpleMsg(String email, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        log.info("from==="+mailProperties.getFrom());
        log.info("to==="+email);
        log.info("setBcc==="+mailProperties.getBcc());
        log.info("setSubject==="+mailProperties.getSubject());
        log.info("text==="+text);
        msg.setFrom(mailProperties.getFrom());
        msg.setTo(email);
        msg.setBcc(mailProperties.getBcc());
        msg.setSubject(mailProperties.getSubject());
        msg.setText(text);
        return msg;
    }
}

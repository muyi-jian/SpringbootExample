package spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class SpringBootEmailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 普通文本内容
     * @return
     */
    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("11@qq.com");
        message.setTo("12@qq.com");
        message.setSubject("主题");
        message.setText("内容");

        javaMailSender.send(message);
    }

    /**
     * html内容
     * @return
     */
    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("11@qq.com");
        helper.setTo("12@163.com");
        helper.setSubject("主题");
        helper.setText("<html><body>验证码：<h1>1213</h1></body></html>", true);
        javaMailSender.send(mimeMessage);
    }


}

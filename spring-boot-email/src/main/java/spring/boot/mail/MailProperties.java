package spring.boot.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties(prefix = "mail") 报错：Not registered via @EnableConfigurationProperties, marked as Spring component, or scanned via @Confi
 * 解决方法一：@Component
 * 解决方法二：@EnableConfigurationProperties({MailProperties.class})
 * @author yangjian
 * @date 2022/11/25 16:32
 */

@Data
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    /**
     * 发件人
     */
    private String from;

    /**
     * 发件人昵称
     */
    private String personal;

    /**
     * 抄送人
     */
    private String bcc;

    /**
     * 主题
     */
    private String subject;

}

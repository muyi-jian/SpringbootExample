/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:50
 * @version 1.0.0
 */
package com.yj.properties.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:50
 * @version 1.0.0
 */
@Data
@PropertySource("classpath:application.yml")
@Component
public class DeveloperProperty2 {
    @Value("${developer.name}")
    private String name;
    @Value("${developer.website}")
    private String website;
    @Value("${developer.qq}")
    private String qq;
    @Value("${developer.phone-number}")
    private String phoneNumber;
}

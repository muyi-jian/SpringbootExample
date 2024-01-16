/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:50
 * @version 1.0.0
 */
package com.yj.properties.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:50
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {

    private String name;
    private String website;
    private String qq;
    private String phoneNumber;
}

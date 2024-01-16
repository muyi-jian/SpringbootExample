/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:48
 * @version 1.0.0
 */
package com.yj.properties.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/16 9:48
 * @version 1.0.0
 */
@Data
@Component
public class ApplicationProperty {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
}

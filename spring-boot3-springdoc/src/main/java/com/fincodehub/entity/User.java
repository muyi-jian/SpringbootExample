package com.fincodehub.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/5/21 21:34
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description="User对象")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 4904447311066487958L;
    @Schema(description = "用户名")
    private String name;
    @Schema(description = "用户年龄")
    private Integer age;
}

package com.yj.swagger.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author YangJian
 * @date 2023/12/31 10:36
 * @description
 */
@Data
@Getter
@ToString

public class Login {
    private String username;
    private String password;
}

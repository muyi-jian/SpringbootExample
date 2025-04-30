package com.fincodehub.mail.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MailInfo
 * @create 2025/4/30 13:42
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {
    private List<String> toUser;
    private String subject;
    private String content;
}

package com.fincodehub.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/5/8 21:24
 * @description <TODO description class purpose>
 */

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    //测试乐观锁
    @Version
    private Integer version;


}

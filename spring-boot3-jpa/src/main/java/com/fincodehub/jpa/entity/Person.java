package com.fincodehub.jpa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author YangJian
 * @version 1.0.0
 * @title Person
 * @create 2025/5/9 9:34
 * @description <TODO description class purpose>
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Person extends BaseEntity {

    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    private Integer age;

}

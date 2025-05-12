package com.fincodehub.jpa.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author YangJian
 * @version 1.0.0
 * @title BaseEntity
 * @create 2025/5/9 9:31
 * @description <TODO description class purpose>
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Version
    protected Long version;
    protected boolean deleted;

    @CreatedBy
    protected Integer createUserId;
    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedBy
    protected Integer lastModifiedUserId;
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
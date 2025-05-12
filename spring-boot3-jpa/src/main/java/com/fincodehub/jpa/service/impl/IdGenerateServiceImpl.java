package com.fincodehub.jpa.service.impl;


import com.fincodehub.jpa.service.IdGenerateService;
import com.fincodehub.jpa.util.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title IdGenerateServiceImpl
 * @create 2025/5/12 8:34
 * @description <TODO description class purpose>
 */
@Service
public class IdGenerateServiceImpl implements IdGenerateService, IdentifierGenerator {
    @Resource(name = "snowflakeIdGenerator")
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public long generateUserId() {
        return snowflakeIdGenerator.nextId();
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return snowflakeIdGenerator.nextId();
    }
}

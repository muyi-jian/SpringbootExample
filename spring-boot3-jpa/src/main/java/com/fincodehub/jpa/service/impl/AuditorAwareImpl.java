package com.fincodehub.jpa.service.impl;


import com.fincodehub.jpa.entity.AuditUser;
import com.fincodehub.jpa.repository.AuditUserRepository;
import com.fincodehub.jpa.service.AuditorAwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title AuditorAwareImpl
 * @create 2025/5/9 8:30
 * @description
 */
@Service
public class AuditorAwareImpl implements AuditorAwareService {
    @Autowired
    private AuditUserRepository auditUserRepository;



    public void testAuditDate(){
        /*
         *不设置创建和修改时间，由springl-data替我们完成
         */
        AuditUser audit = AuditUser.builder().name("张三").build();
        AuditUser save = auditUserRepository.save(audit);
        System.out.println(save);
    }


    public void testAuditUser(){

        /*
         * 模拟当前用户
         */

        /*
         * 这里不设置是谁保存的，看spring-data是否会为我们完成
         */
        AuditUser audit = AuditUser.builder().name("李四").build();

        AuditUser save = auditUserRepository.save(audit);
        System.out.println(save);
    }
}

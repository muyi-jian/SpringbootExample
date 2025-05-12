package com.fincodehub.jpa.service.impl;


import com.fincodehub.jpa.config.CustomAuditor;
import com.fincodehub.jpa.entity.LoginUser;
import com.fincodehub.jpa.entity.Person;
import com.fincodehub.jpa.repository.LoginUserRepository;
import com.fincodehub.jpa.repository.PersonRepository;
import com.fincodehub.jpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangJian
 * @version 1.0.0
 * @title PersonServiceImpl
 * @create 2025/5/9 9:41
 * @description <TODO description class purpose>
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    CustomAuditor myAuditorAware;
    @Override
    public void save() {
        LoginUser loginUser = loginUserRepository.findByName("a2");
        System.out.println(loginUser);
        System.out.println(loginUser.getId());
        myAuditorAware.setCurrentUser(loginUser);
        Person person = new Person();
        personRepository.save(person);
    }

    public void testAuditDate(){
        /*
         *不设置创建和修改时间，由springl-data替我们完成
         */
        Person audit = Person.builder().name("张三").build();
        Person save = personRepository.save(audit);
        System.out.println(save);
    }


    public void testAuditUser(){

        /*
         * 模拟当前用户
         */
        LoginUser loginUser = loginUserRepository.findByName("a2");
        System.out.println(loginUser);
        System.out.println(loginUser.getId());
        myAuditorAware.setCurrentUser(loginUser);

        /*
         * 这里不设置是谁保存的，看spring-data是否会为我们完成
         */
        Person audit = Person.builder().name("李四").build();

        System.out.println(audit);
        Person save = personRepository.save(audit);
        System.out.println(save);
    }
}

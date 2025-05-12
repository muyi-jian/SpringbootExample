package com.fincodehub.jpa.service.impl;


import com.fincodehub.jpa.entity.LoginUser;
import com.fincodehub.jpa.repository.LoginUserRepository;
import com.fincodehub.jpa.service.LoginUserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserServiceImpl
 * @create 2025/5/9 10:31
 * @description <TODO description class purpose>
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    public LoginUserRepository loginUserRepository;
    @Override
    public void findAll(LoginUser loginUser){
        //JPA的分页查询，页码从0开始,这里要减1
        PageRequest pageRequest = PageRequest.of(1-1, 2, Sort.by("username").descending());

        // 查询全部
        // Page<LoginUser> all = loginUserRepository.findAll(pageRequest);

        // 动态条件查询
        Specification<LoginUser> specification = (root, query, criteriaBuilder) -> {
            // 添加查询条件
            List<Predicate> predicates = new ArrayList<>();
            if (loginUser.getUsername() != null){
                predicates.add( criteriaBuilder.like(root.get("username"), "%"+loginUser.getUsername()+"%"));
            }
            return criteriaBuilder.and((predicates.toArray(new Predicate[0])));
        };
        Page<LoginUser> all = loginUserRepository.findAll(specification, pageRequest);
        System.out.println("totalElements::"+all.getTotalElements());
        List<LoginUser> content = all.getContent();
        System.out.println("content::"+content.size());
        for (LoginUser user : content) {
            System.out.println(user.toString());
        }
    }
}

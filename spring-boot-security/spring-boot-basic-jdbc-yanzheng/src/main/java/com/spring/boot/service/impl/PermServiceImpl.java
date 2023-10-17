package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.entity.Perm;
import com.spring.boot.mapper.PermMapper;
import com.spring.boot.service.PermService;
import org.springframework.stereotype.Service;

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {
}

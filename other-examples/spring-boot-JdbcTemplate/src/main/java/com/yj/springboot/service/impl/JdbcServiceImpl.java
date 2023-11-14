package com.yj.springboot.service.impl;

import com.yj.springboot.dao.JdbcDao;
import com.yj.springboot.entity.Student;
import com.yj.springboot.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/12/6 19:55
 */
@Service
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    JdbcDao jdbcDao;

    @Override
    public int addStudent(Student student) {
        return jdbcDao.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return jdbcDao.updateStudent(student);
    }

    @Override
    public int deleteStudentById(String sno) {
        return jdbcDao.deleteStudentById(sno);
    }

    @Override
    public Student getStudentById(String sno) {
        return jdbcDao.getStudentById(sno);
    }

    @Override
    public List<Student> getAllStudents() {
        return jdbcDao.getAllStudents();
    }



}

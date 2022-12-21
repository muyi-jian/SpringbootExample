package com.yj.springboot.service;

import com.yj.springboot.entity.Student;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/12/6 19:55
 */
public interface JdbcService {
     int addStudent(Student student);

     int updateStudent(Student student) ;

     int deleteStudentById(String sno) ;

     Student getStudentById(String sno);

     List<Student> getAllStudents() ;
}

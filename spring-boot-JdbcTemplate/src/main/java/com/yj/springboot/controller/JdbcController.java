package com.yj.springboot.controller;

import com.yj.springboot.entity.Student;
import com.yj.springboot.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/12/6 19:58
 */
@RestController
public class JdbcController {
    @Autowired
    JdbcService jdbcService;

    @GetMapping("/studentOps")
    public String studentOps(){
        Student student = new Student();
        student.setSno("004");
        student.setSname("tom");
        student.setSsex("22");
        int i = jdbcService.addStudent(student);
        System.out.println("add-->>"+i);
        Student student1 = jdbcService.getStudentById("004");
        System.out.println("getStudentById-->>"+student1);
        Student student2 = new Student();
        student2.setSno("004");
        student2.setSname("tom222");
        student2.setSsex("23");
        int i1 = jdbcService.updateStudent(student2);
        System.out.println("update-->>"+i1);
        Student student3 = jdbcService.getStudentById("004");
        System.out.println("getStudentById-3->>"+student3);

        int i2 = jdbcService.deleteStudentById("004");
        System.out.println("deleteStudentById-->>"+i2);
        List<Student> allStudents = jdbcService.getAllStudents();
        allStudents.forEach(System.out::println);

        return "ok";
    }
}

package com.yj.springboot.dao;

import com.yj.springboot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangjian
 * @date 2022/12/6 19:57
 */
@Repository
public class JdbcDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addStudent(Student student) {
        return jdbcTemplate.update("INSERT INTO student (SNO , SNAME,SSEX) VALUES (?, ?,?)",
                student.getSno(), student.getSname(),student.getSsex());
    }

    public int updateStudent(Student student) {
        return jdbcTemplate.update("UPDATE student SET sname=?, ssex=? WHERE sno=?",
                student.getSno(), student.getSname(),student.getSsex());
    }

    public int deleteStudentById(String sno) {
        return jdbcTemplate.update("DELETE FROM student WHERE sno=?", sno);
    }

    public Student getStudentById(String sno) {
        return jdbcTemplate.queryForObject("select * from student where sno=?",
                new BeanPropertyRowMapper<>(Student.class), sno);
    }

    public List<Student> getAllStudents() {
        return jdbcTemplate.query("select * from student",
                new BeanPropertyRowMapper<>(Student.class));
    }
}

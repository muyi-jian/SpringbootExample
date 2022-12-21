package spring.boot.service;

import spring.boot.entity.Student;

/**
 * @author yangjian
 * @date 2022/12/6 19:01
 */
public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    Student queryStudentBySno(String sno);
}

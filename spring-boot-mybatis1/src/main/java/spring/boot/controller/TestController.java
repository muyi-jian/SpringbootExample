package spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.entity.Student;
import spring.boot.service.StudentService;
import spring.boot.util.CommonUtil;

/**
 * @author yangjian
 * @date 2022/12/6 19:03
 */
@RestController
public class TestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping( value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return this.studentService.queryStudentBySno(sno);
    }

    @RequestMapping("/inserBo")
    public void insertBo(){
        String uuid = CommonUtil.get10UUID(10);
        int num =  this.studentService.insertBo(uuid);
    }
}

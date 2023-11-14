package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.boot.entity.Student;

import java.util.ArrayList;

/**
 * @author yangjian
 * @date 2022/11/23 16:40
 */
@Controller
public class I18nController {


    @GetMapping("/i18n")
    public String index(Model model) {
        //return messageSource.getMessage("index.welcome", null, LocaleContextHolder.getLocale());
            Student stu1 = new Student("张三", 20, "1155@qq.com", "13333835901");
            Student stu2 = new Student("李四", 21, "1154@qq.com", "13333835902");
            Student stu3 = new Student("王五", 22, "1153@qq.com", "13333835903");
            Student stu4 = new Student("小芳", 23, "1156@qq.com", "13333835904");
            ArrayList<Student> stus = new ArrayList<>();
            stus.add(stu1);
            stus.add(stu2);
            stus.add(stu3);
            stus.add(stu4);
            model.addAttribute("stus", stus);
            return "i18n";
    }
}

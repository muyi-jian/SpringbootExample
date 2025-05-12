package com.fincodehub.jpa.controller;


import com.fincodehub.jpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJian
 * @version 1.0.0
 * @title PersonController
 * @create 2025/5/9 9:39
 * @description <TODO description class purpose>
 */
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/person/save")
    public void test() {
        personService.save();
    }
    @GetMapping("/person/tu")
    public void testAuditDate(){
        personService.testAuditDate();
    }
    @GetMapping("/person/tu1")
    public void testAuditUser() {
        personService.testAuditUser();
    }
}

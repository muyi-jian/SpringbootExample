package spring.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.pojo.Employee;
import spring.boot.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 21:05
 */
@RestController
@RequestMapping("employee")
@Api(value = "员工接口表")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取员工实体类集合
     * @return
     */
    @GetMapping
    @ApiOperation(value = "获取员工数据", notes = "返回类型为List")
    public List<Employee> getFormCounters() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("zhangsan","20220101","张","三"));
        return employeeList;
    }


}

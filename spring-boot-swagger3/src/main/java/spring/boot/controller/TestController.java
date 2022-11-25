package spring.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.pojo.User;
import spring.boot.service.UserService;
import spring.boot.util.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjian
 * @date 2022/11/24 22:00
 */
@RestController
@RequestMapping("/user")
//定义swagger页面信息
@Api(tags = "人员信息查询接口")
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping("/queryPage")
    @ApiOperation(value = "人员信息分页查询方法", notes = "查看人员信息是否返回成功")
    public ResultUtil queryPage(Integer pageNumber, Integer pageSize, String name) {
        List<User> list = new ArrayList<User>();
        return ResultUtil.success(200,"成功", list);
    }
    @GetMapping("/test")
    @ApiOperation(value = "人员是否存在查询方法", notes = "查看人员是否存在返回成功")
    public ResultUtil<Boolean> test() {
        return ResultUtil.success(true);
    }
}
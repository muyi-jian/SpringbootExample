package com.fincodehub.mybatis.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fincodehub.mybatis.entity.LoginUser;
import com.fincodehub.mybatis.result.ResponseResult;
import com.fincodehub.mybatis.service.LoginUserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LoginUserController
 * @create 2025/4/29 16:39
 * @description <TODO description class purpose>
 */
@RestController
public class LoginUserController {

    private final LoginUserService loginUserService;

    public LoginUserController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping("/user/{id}")
    public LoginUser findById(@PathVariable("id") Integer id) {
        return loginUserService.findById(id);
    }

    @PostMapping("/user")
    public String saveData(@RequestBody LoginUser loginUser) {
        return loginUserService.saveData(loginUser) ? "保存成功" : "保存失败";
    }

    @RequestMapping("/getPage/{pagenum}/{pageSize}")
    public ResponseResult<IPage<LoginUser>> getPageData(@PathVariable("pagenum") int pagenum, @PathVariable("pageSize") int pageSize) {
        IPage<LoginUser> pageData = loginUserService.getPageData(pagenum, pageSize);
        return ResponseResult.success("查询成功", pageData);
    }

    @GetMapping("/user/list")
    public ResponseResult list(){

        // 自动注入 LoginUserMapper 接口对应的实现类对象
        // 创建分页查询相关参数 page，泛型为 LoginUser，表示查询到的结果对应的实体类为LoginUser
        Page<LoginUser> page = new Page<>();
        // 设置分页查询显示第二页的数据，实际开发过程中该参数有前端传递
        page.setCurrent(2);
        // 设置分页查询每页显示四条数据，实际开发过程中该参数有前端传递
        page.setSize(2);
        // 创建排序字段集合，不想排序不加即可，实际开发过程中一般都会要求按照时间降序排序
        List<OrderItem> orders = new ArrayList<>();
        // 按照价格排序，排序方式为降序，ASC为True表示升序，false表示降序，第一个参数表示数据库中的列名
        orders.add(OrderItem.asc("username"));
        // 按照生产时间排序，排序方式为降序
        orders.add(OrderItem.desc("nick_name"));
        // 将排序对象集合加入分页查询对象Page中
        page.setOrders(orders);
        // 执行分页查询，可以创建一个Page对象接受查询结果，
        // 也可以用查询条件参数page，但其实最后结果都是同一个
        page = loginUserService.selectPage(page, null);
        // 可以新创建一个Page对象，就像下面这样
        Page<LoginUser> LoginUserPage = loginUserService.selectPage(page, null);
        // 输出分页查询结果显示当前的哪一页
        System.out.println("当前页："+page.getCurrent());
        // 输出分页查询结果的总数据条数
        System.out.println("总数据条数："+page.getTotal());
        // 输出分页查询结果的数据集合
        System.out.println("数据集合："+page.getRecords());
        // 输出分页查询结果的每页显示条数
        System.out.println("每页显示条数："+page.getSize());
        // 判断刚才分页查询的两个结果对象是否为同一个
        System.out.println("分页查询的两个结果对象是否为同一个："+(page == LoginUserPage));
        // 输出第一个分页查询对象内存地址
        System.out.println("分页查询对象内存地址："+page);
        // 输出第二个分页查询对象内存地址
        System.out.println("第二个分页查询对象内存地址："+LoginUserPage);
        return ResponseResult.success();
    }
}

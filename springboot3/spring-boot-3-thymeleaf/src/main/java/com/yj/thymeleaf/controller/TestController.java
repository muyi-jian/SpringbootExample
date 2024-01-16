/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/15 21:29
 * @version 1.0.0
 */
package com.yj.thymeleaf.controller;

import com.yj.thymeleaf.entity.TH;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/15 21:29
 * @version 1.0.0
 */
@Controller
public class TestController {
    @GetMapping({"/","/index"})
    public String  listUser(Model model){
        model.addAttribute("name","修身养性");
        return "index";
    }
    @GetMapping("/testth")
    public String testth(Model model,HttpSession session){
        List<TH> list = new ArrayList<>();

        list.add(new TH("${...}","变量表达式：取出上下文变量的值","<p th:text=\"${collect.description}\">description</p>"));
        list.add(new TH("*{...}","选择变量表达式：取出选择的队形的属性值","<div th:object=\"${session.user}\"><p th:text=\"*{name}\"></p></p></div>或者<p th:text=\"*{session.user.name}\"></p>"));
        list.add(new TH("#{...}","消息表达式：使文字消息国际化，I18N","<p th:text=\"#{welcom.message}\"></p>"));
        list.add(new TH("@{...}","链接表达式：用于表示各种超链接地址","<p th:text=\"@{https://fanlychie.github.io}\"></p>"));
        list.add(new TH("~{...}","片段表达式：引用一段公共的代码片段","<div th:insert=\"~{base :: footerFragment}\"></div>"));

        list.add(new TH("th:id","替换id","<input th:id=\"'xxx' + ${collect.id}\"/>"));
        list.add(new TH("th:text","文本替换","<p th:text=\"${collect.description}\">description</p>"));
        list.add(new TH("th:utext","支持html的文本替换","<p th:utext=\"${htmlcontent}\">conten</p>"));
        list.add(new TH("th:object","替换对象","<div th:object=\"${session.user}\">"));
        list.add(new TH("th:value","属性赋值","<input th:value=\"${user.name}\" />"));
        list.add(new TH("th:with","变量赋值运算","<div th:with=\"isEven=${prodStat.count}%2==0\"></div>"));
        list.add(new TH("th:style","设置样式","th:style=\"'display:' + @{(${sitrue} ? 'none' : 'inline-block')} + ''"));
        list.add(new TH("th:onclick","点击事件","\tth:onclick=\"'getCollect()'\""));
        list.add(new TH("th:each","属性赋值","\ttr th:each=\"user,userStat:${users}\">"));
        list.add(new TH("th:if","判断条件","<a th:if=\"${userId == collect.userId}\" >"));
        list.add(new TH("th:unless","和th:if判断相反","<a th:href=\"@{/login}\" th:unless=${session.user != null}>Login</a>"));
        list.add(new TH("th:href","链接地址","<a th:href=\"@{/login}\" th:unless=${session.user != null}>Login</a> />"));
        list.add(new TH("th:switch","多路选择 配合th:case 使用","<div th:switch=\"${user.role}\">"));
        list.add(new TH("th:case","th:switch的一个分支","<p th:case=\"'admin'\">User is an administrator</p>"));
        list.add(new TH("th:fragment","布局标签，定义一个代码片段，方便其它地方引用","<div th:fragment=\"alert\">"));
        list.add(new TH("th:include","布局标签，替换内容到引入的文件","<head th:include=\"layout :: htmlhead\" th:with=\"title='xx'\"></head> />"));
        list.add(new TH("th:replace","布局标签，替换整个标签到引入的文件","<div th:replace=\"fragments/header :: title\"></div>"));
        list.add(new TH("th:selected","selected选择框 选中","th:selected=\"(${xxx.id} == ${configObj.dd})\""));
        list.add(new TH("th:src","图片类地址引入","<img class=\"img-responsive\" alt=\"App Logo\" th:src=\"@{/img/logo.png}\" />"));
        list.add(new TH("th:inline","定义js脚本可以使用变量","<script type=\"text/javascript\" th:inline=\"javascript\">"));
        list.add(new TH("th:action","表单提交的地址","<form action=\"subscribe.html\" th:action=\"@{/subscribe}\">"));
        list.add(new TH("th:remove","删除某个属性","<tr th:remove=\"all\"> 1.all:删除包含标签和所有的孩子。2.body:不包含标记删除,但删除其所有的孩子。3.tag:包含标记的删除,但不删除它的孩子。4.all-but-first:删除所有包含标签的孩子,除了第一个。5.none:什么也不做。这个值是有用的动态评估"));
        list.add(new TH("th:attr","设置标签属性，多个属性可以用逗号分隔","比如th:attr=\"src=@{/image/aa.jpg},title=#{logo}\"，此标签不太优雅，一般用的比较少"));




        model.addAttribute("collects",list);
        session.setAttribute("user",new TH("TH_USER","测试员","测试案例"));
        return "testth";
    }
}
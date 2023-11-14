<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
    <form action="/servlet_demo/myservlet1" method="post">
        <input type="text" name="username" id="username" placeholder="请填写用户名..."> <br>
        <input type="text" name="password" id="password" placeholder="请填写用密码..."><br>
        <input type="number" name="age" id="age" placeholder="请填写年龄..."><br>
        option1:<input type="radio" id="radio1" name="radios" value="option1"><br>
        option2:<input type="radio" id="radio2" name="radios" value="option2"><br>
        option3:<input type="radio" id="radio3" name="radios" value="option3"><br>

        checkbox1:<input type="checkbox" id="checkbox1" name="checkboxs" value="checkbox1"><br>
        checkbox1:<input type="checkbox" id="checkbox2" name="checkboxs" value="checkbox2"><br>
        checkbox1:<input type="checkbox" id="checkbox3" name="checkboxs" value="checkbox3"><br>

        <textarea name="shuoming" placeholder="请填写说明..."></textarea><br>

        <input type="submit" value="submit">
    </form>
</body>
</html>

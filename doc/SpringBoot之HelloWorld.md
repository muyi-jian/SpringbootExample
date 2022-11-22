### SpringBoot之HelloWorld

   这里使用Intellij中的Spring Initializr⼯具

- 单栏中选择 File  => New  => Project..  ，我们可以看到如下图所示的创建功能窗⼝。其
    中 Initial Service Url  指向的地址就是Spring官⽅提供的Spring Initializr⼯具地址，所以这⾥创
    建的⼯程实际上也是基于它的Web⼯具来实现的。

    Service Url地址有两个选择：

    官方：https://start.spring.io/

    阿里：http://start.aliyun.com或https://start.aliyun.com

    <img src="F:\学习笔记\Notes\attachment\SpringBoot之HelloWorld.assets\image-20221121214335058.png" alt="image-20221121214335058" style="zoom: 80%;" />

- 选择版本和所需的技术

    <img src="F:\学习笔记\Notes\attachment\SpringBoot之HelloWorld.assets\image-20221121214546434.png" alt="image-20221121214546434" style="zoom:80%;" />

- 创建成功后，项目结构如下

    <img src="F:\学习笔记\Notes\attachment\SpringBoot之HelloWorld.assets\image-20221121214757905.png" alt="image-20221121214757905" style="zoom:80%;" />

- 创建一个HelloWorldController

    ```java
    package com.springboot.controller;
    
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    /**
     * @author yangjian
     * @date 2022/11/21 21:48
     */
    @RestController
    public class HelloWorldController {
    
        @GetMapping("helloWorld")
        public String helloWorld(){
            return "HelloWorld";
        }
    }
    
    ```

    

- 运行，浏览器输入http://localhost:8080/helloWorld

<img src="F:\学习笔记\Notes\attachment\SpringBoot之HelloWorld.assets\image-20221121215805098.png" alt="image-20221121215805098" style="zoom:80%;" />

- 打包发布

    
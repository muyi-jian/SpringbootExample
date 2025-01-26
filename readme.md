### SpringBoot学习示例教程

此教程是一个用来学习并实战 `spring boot` 的项目，帮助初学者快速掌握 Spring Boot 各组件的使用。

### 本项目中所有示例均已经包含到 Spring Boot 2.0，Spring Boot 3.0,版本。

- Spring Boot 2.X 系列示例代码目录格式：[spring-boot2-xx]
- Spring Boot 3.X 系列示例代码目录格式：[spring-boot3-xx]

### 示例代码

| Module 名称                                                                                                                                     | Module 介绍                                                  |
|-----------------------------------------------------------------------------------------------------------------------------------------------| ------------------------------------------------------------ |
| [spring-boot3-hello](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-hello)                              | Spring Boot 3 的一个 helloworld                              |
| [spring-boot3-properties](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-properties)                   | Spring Boot 3 中演示读取配置文件中的内容                     |
| [spring-boot3-actuator](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-actuator)                       | Spring Boot 3 集成 spring-boot-starter-actuator 用于监控 Spring Boot 3 的启动和运行状态 |
| [spring-boot3-admin-client](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-admin/admin-client)         | Spring Boot 3 集成 spring-boot-admin 来可视化的监控 Spring Boot 3 程序的运行状态，可以与 actuator 互相搭配使用，客户端示例 |
| [spring-boot3-admin-server](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-admin/admin-server)         | Spring Boot 3 集成 spring-boot-admin 来可视化的监控 Spring Boot 3 程序的运行状态，可以与 actuator 互相搭配使用，服务端示例 |
| [spring-boot3-logback](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-logback)                         | Spring Boot 3 集成 logback 日志                              |
| [spring-boot3-log-aop](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-log-aop)                         | Spring Boot 3 使用 AOP 切面的方式记录 web 请求日志           |
| [spring-boot3-exception-handler](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-exception-handler)     | Spring Boot 3 统一异常处理，包括2种，第一种返回统一的 json 格式，第二种统一跳转到异常页面 |
| [spring-boot3-template-freemarker](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-template-freemarker) | Spring Boot 3 集成 Freemarker 模板引擎                       |
| [spring-boot3-template-thymeleaf](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-template-thymeleaf)   | Spring Boot 3 集成 Thymeleaf 模板引擎                        |
| [spring-boot3-template-beetl](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-template-beetl)           | Spring Boot 3 集成 Beetl 模板引擎                            |
| [spring-boot3-template-enjoy](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-template-enjoy)           | Spring Boot 3 集成 Enjoy 模板引擎（待）                      |
| [spring-boot3-jdbctemplate](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-jdbctemplate)               | Spring Boot 3 集成 Jdbc Template 操作数据库，并简易封装通用 Dao 层 |
| [spring-boot3-jpa](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-jpa)                                 | Spring Boot 3 集成 spring-boot-starter-data-jpa 操作数据库   |
| [spring-boot3-mybatis](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-mybatis)                         | Spring Boot 3 集成原生mybatis，使用 [mybatis-spring-boot-starter](https://github.com/mybatis/spring-boot-starter) 集成 |
| [spring-boot3-mybatis-plus](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-mybatis-plus)               | Spring Boot 3 集成 [mybatis-plus](https://mybatis.plus/)，使用 [mybatis-plus-boot-starter](http://mp.baomidou.com/) 集成，集成 BaseMapper、BaseService、ActiveRecord 操作数据库 |
| [spring-boot3-upload](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-upload)                           | Spring Boot 3 文件上传示例，包含本地文件上传以及七牛云文件上传 |
| [spring-boot3-cache-redis](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-cache-redis)                 | Spring Boot 3 整合 redis，操作redis中的数据，并使用redis缓存数据 |
| [spring-boot3-email](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-email)                             | Spring Boot 3 整合 email，包括发送简单文本邮件、HTML邮件（包括模板HTML邮件）、附件邮件、静态资源邮件 |
| [spring-boot3-task](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-task)                               | Spring Boot 3 快速实现定时任务                               |
| [spring-boot3-quartz](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-quartz)                           | Spring Boot 3 整合 quartz，并实现对定时任务的管理，包括新增定时任务，删除定时任务，暂停定时任务，恢复定时任务，修改定时任务启动时间，以及定时任务列表查询，`提供前端页面` |
| [spring-boot3-springdoc](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-springdoc)                     | Spring Boot 3 集成原生的 `springdoc` 用于统一管理、测试 API 接口 |
| [spring-boot3-rbac-security](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-rbac-security)             | Spring Boot 3 集成 spring security 完成基于RBAC权限模型的权限管理，支持自定义过滤请求，动态权限认证，使用 JWT 安全认证，支持在线人数统计，手动踢出用户等操作 |
| [spring-boot3-rbac-shiro](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-rbac-shiro)                   | Spring Boot 3 集成 shiro 实现权限管理 (待)                   |
| [spring-boot3-session](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-session)                         | Spring Boot 3 集成 Spring Session 实现Session共享、重启程序Session不失效（待） |
| [spring-boot3-oauth](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-oauth)                             | Spring Boot 3 实现 oauth 服务器功能，实现授权码机制 待完成（待） |
| [spring-boot3-social](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-social)                           | Spring Boot 3 集成第三方登录，集成 `justauth-spring-boot-starter` 实现QQ登录、GitHub登录、微信登录、谷歌登录、微软登录、小米登录、企业微信登录。（待） |
| [spring-boot3-mq-rabbitmq](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-mq-rabbitmq)                 | Spring Boot 3 集成 RabbitMQ 实现基于直接队列模式、分列模式、主题模式、延迟队列的消息发送和接收 |
| [spring-boot3-mq-rocketmq](https://github.com/muyi-jian/SpringbootExample/tree/master/spring-boot3-mq-rocketmq)                 | Spring Boot 3 集成 RocketMQ，实现消息的发送和接收 待完成     |

### 开发环境

以下版本只是代码演示版本

- **SpringBoot 2.x**
    - **JDK 1.8 +**
    - **Maven 3.5 +**
    - **IntelliJ IDEA 2023.1** (*注意：务必使用 IDEA 开发，同时保证安装 `lombok` 插件*)
    - **Mysql 5.7 +**
- **SpringBoot 3.x**
    - **JDK17+**
    - **Maven 3.5 +**
    - **IntelliJ IDEA 2023.1 ** (*注意：务必使用 IDEA 开发，同时保证安装 `lombok` 插件*)
    - **Mysql 5.7 +**

### 部署到IDEA

> 1. `git clone git@github.com:muyi-jian/SpringbootExample.git`
> 2. 使用 IDEA 打开 clone 下来的项目
> 3. 在 IDEA 中 Maven Projects 的面板导入项目根目录下 的 `pom.xml` 文件
> 4. 找到各个 module 的 Application 类就可以运行各个 demo 了

注意：此教程仅为个人理解和参考网上教程制作，若有错误请见谅。

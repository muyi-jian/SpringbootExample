### 什么是SpringBoot

官方文档《[spring-boot-reference2.7.5.pdf](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/getting-started.html#getting-started)》：

Spring Boot helps you to create stand-alone, production-grade Spring-based applications that you
can run. We take an opinionated view of the Spring platform and third-party libraries, so that you
can get started with minimum fuss. Most Spring Boot applications need very little Spring
configuration.

1. SpringBoot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用搭建和开发过程的一种框架；
2. SpringBoot完成了对各种框架的整合，让这些框架集成在一起变得更加简单，简化了我们在集成过程中繁琐的模板化配置；
3. 从最根本上来讲，Spring Boo是一个启动Spring项目的工具，是一些库的集合；
4. SpringBoot不是一个全新的框架，也不是Spring解决方案的替代品，而是对Spring框架的一个封装。所以，以前Spring可以做的事情，现在用SpringBoot都可以做；
5. 一般情况下，一个SpringBoot应用 = 一个微服务 = 一个模块 = 一个有边界的上下文；
6. SpringBoot是整合Spring技术栈的一站式框架，是简化Spring技术栈的快速开发脚手架，是一个能够快速构建生产级别的Spring应用的工具。

简单来说SpringBoot是：

简化Spring应用开发的一个框架；
整个Spring技术栈的一个大整合；
J2EE开发的一站式解决方案；

#### Spring Boot的特征-为什么要用Springboot

## Features

- Create stand-alone Spring applications
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Provide opinionated 'starter' dependencies to simplify your build configuration
- Automatically configure Spring and 3rd party libraries whenever possible
- Provide production-ready features such as metrics, health checks, and externalized configuration
- Absolutely no code generation and no requirement for XML configuration

1. 独立运行：SpringBoot开发的应用可以以JRA包的形式独立运行，运行一个SpringBoot应用只需通过 java –jar xxxx.jar 来运行；
2. 内嵌容器：SpringBoot内嵌了多个WEB容器，如：Tomcat、Jetty、Undertow，所以可以使用非WAR包形式进行项目部署；
3. 自动starter依赖：SpringBoot提供了一系列的starter来简化Maven的依赖加载。starter是一组方便的依赖关系描述符，它将常用的依赖分组并将其合并到一个依赖中，这样就可以一次性将相关依赖添加到Maven或Gradle中；
4. 自动配置：SpringBoot会根据在类路径中的JAR包和类，自动将类注入到SpringBoot的上下文中，极大地减少配置的使用；
5. 应用监控：SpringBoot提供基于http、ssh、telnet的监控方式，对运行时的项目提供生产级别的服务监控和健康检测；
6. 无代码生成/无需编写XML配置：SpringBoot不是借助于代码生成来实现的，而是通过条件注解来实现的，这是 Spring 4.x 提供的新特性。Spring4.x提倡使用Java配置和注解组合，无需编写任何xml配置即可实现Spring的所有配置功能；

#### Spring Boot的缺点

1. 减少开发
2. 使用javaConfig有助于避免使用XML
3. 避免大量的Maven导入和各种版本冲突
4. 需要更少的配置文件，只需要注解来完成


#### Spring Boot的缺点

Spring Boot虽然上手很容易，但如果你不了解其核心技术及流程，所以一旦遇到问题就很棘手，而且现在的解决方案也不是很多，需要一个完善的过程。

1、迭代快
2、Spring Boot虽然上手很容易，封装太深，内部原理复杂，不容易精通

#### 运行SpringBoot有哪几种方式

1. 打包用命令或者放到容器中运行
2. 使用maven插件运行
3. 直接执行main方法运行

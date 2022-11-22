### Spring的顶级项目有哪些？

#### MVC、Spring、SpringMVC、SpringBoot、SpringCloud的区别是什么？

1. MVC：MVC是一种设计模式，即Model模型、View视图以及Controller控制器；
2. Spring：Spring是一个开源框架，是在2003年兴起的一个轻量级的Java开发框架，由Rod Johnson在其著作Expert One-On-One J2EE Development and Design中阐述的部分理念和原型衍生而来。它是为了解决企业应用开发的复杂性而创建的，框架的主要优势之一就是其分层架构，分层架构允许使用者选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。Spring的用途不仅限于服务器端的开发，从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。Spring的核心是控制反转（IoC）和面向切面（AOP），简单来说，Spring是一个分层的JavaSE/EEfull-stack(一站式) 轻量级开源框架；
3. SpringMVC：SpringMVC是一种WEB层的MVC框架，它是spring的一个模块，属于SpringFrameWork的后续产品，拥有spring的特性。SpringMVC分离了控制器、模型对象、分派器以及处理程序对象的角色；
4. Spring Boot：它不是一个全新的框架，也不是Spring解决方案的替代品，而是对Spring框架的一个封装。所以，以前Spring可以做的事情，现在用SpringBoot都可以做；
5. Spring Cloud：Sping Cloud是Spring的一个顶级项目，是一个微服务框架，提供了全套的分布式应用系统的解决方案。为开发者提供了快速构建分布式系统的工具，使其可以快速的启动服务、构建应用、同时能够快速和云平台资源进行对接。

#### Spring的顶级项目有哪些？

1. Spring IO platform：用于系统部署，是可集成的，构建现代化应用的版本平台，具体来说当你使用maven dependency引入spring jar包时它就在工作了。
2. Spring Boot：旨在简化创建产品级的 Spring 应用和服务，简化了配置文件，使用嵌入式web服务器，含有诸多开箱即用微服务功能，可以和spring cloud联合部署。
3. Spring Framework：即通常所说的spring框架，是一个开源的Java/Java EE全功能栈应用程序框架，其它spring项目如spring boot也依赖于此框架。
4. Spring Cloud：微服务工具包，为开发者提供了在分布式系统的配置管理、服务发现、断路器、智能路由、微代理、控制总线等开发工具包。
5. Spring XD：是一种运行时环境（服务器软件，非开发框架），组合spring技术，如spring batch、spring boot、spring data，采集大数据并处理。
6. Spring Data：是一个数据访问及操作的工具包，封装了很多种数据及数据库的访问相关技术，包括：jdbc、Redis、MongoDB、Neo4j等。
7. Spring Batch：批处理框架或说是批量任务执行管理器，功能包括任务调度、日志记录/跟踪等。
8. Spring Security：是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架。
9. Spring Integration：面向企业应用集成（EAI/ESB）的编程框架，支持的通信方式包括HTTP、FTP、TCP/UDP、JMS、RabbitMQ、Email等。
10. Spring Social： 一组工具包，用于连接社交服务API，如Twitter、Facebook、LinkedIn、GitHub等。
11. Spring AMQP：消息队列操作的工具包，主要是封装了RabbitMQ的操作。
12. Spring HATEOAS：是一个用于支持实现超文本驱动的REST Web服务的开发库。
13. Spring Mobile：是Spring MVC的扩展，用来简化手机上的Web应用开发。
14. Spring for Android：是Spring框架的一个扩展，其主要目的在于简化Android本地应用的开发，提供RestTemplate来访问Rest服务。
15. Spring Web Flow：目标是成为管理Web应用页面流程的最佳方案，将页面跳转流程单独管理，并可配置。
16. Spring LDAP：是一个用于操作LDAP的Java工具包，基于Spring的JdbcTemplate模式，简化LDAP访问。
17. Spring Session：session管理的开发工具包，让你可以把session保存到redis等，进行集群化session管理。
18. Spring Web Services：是基于Spring的Web服务框架，提供SOAP服务开发，允许通过多种方式创建Web服务。
19. Spring Shell：提供交互式的Shell可让你使用简单的基于Spring的编程模型来开发命令，比如Spring Roo命令。
20. Spring Roo：是一种Spring开发的辅助工具，使用命令行操作来生成自动化项目，操作非常类似于Rails。
21. Spring Scala：为Scala语言编程提供的spring框架的封装。
22. Spring BlazeDS Integration：一个开发RIA工具包，可以集成Adobe Flex、BlazeDS、Spring以及Java技术创建RIA。
23. Spring Loaded：用于实现java程序和web应用的热部署的开源工具。
24. Spring REST Shell：可以调用Rest服务的命令行工具，敲命令行操作Rest服务。
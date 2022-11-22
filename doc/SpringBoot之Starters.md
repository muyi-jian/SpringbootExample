### SpringBoot之Starters

#### Starters是什么

简单说Starters可以理解为启动器，包含集成到应用里面的依赖包，Starters包含了许多项目中需要用到的依赖

Starters可以理解为启动器，它包含了一系列可以集成到应用里面的依赖包，你可以一站式集成Spring 及其他技术，而不需要到处找示例代码和依赖包。如你想使用Spring JPA访问数据库，只要加入springboot-starter-data-jpa启动器依赖就能使用了。Starters包含了许多项目中需要用到的依赖，它们能快速持续的运行，都是一系列得到支持的管理传递性依赖。

#### Starters命名方式

 Spring Boot官方的启动器都是以spring-boot-starter-命名的，代表了一个特定的应用类型。

第三方的 启动器不能以spring-boot开头命名，它们都被Spring Boot官方保留。一般一个第三方的应该这样命 名，像mybatis的mybatis-spring-boot-starter。

#### Starters分类

1. ##### Spring Boot应用类启动器

    | 启动器名称              | 功能描述                                                     |
    | :---------------------- | :----------------------------------------------------------- |
    | spring-boot-starter     | 包含自动配置、日志、YAML的支持。                             |
    | spring-boot-starter-web | 使用Spring MVC构建web 工程，包含restful，默认使用Tomcat容器。 |
    | ...                     | ...                                                          |

2. ##### Spring Boot生产启动器

    | 启动器名称                   | 功能描述                           |
    | :--------------------------- | :--------------------------------- |
    | spring-boot-starter-actuator | 提供生产环境特性，能监控管理应用。 |

3. ##### Spring Boot技术类启动器

    | 启动器名称                  | 功能描述                            |
    | :-------------------------- | :---------------------------------- |
    | spring-boot-starter-json    | 提供对JSON的读写支持。              |
    | spring-boot-starter-logging | 默认的日志启动器，默认使用Logback。 |
    | ...                         | ...                                 |

4. 其他第三方启动器

5. SpringBoot应用启动器

    1）spring-boot-starter 
    这是Spring Boot的核心启动器，包含了自动配置、日志和YAML。

    2）spring-boot-starter-actuator 
    帮助监控和管理应用。

    3）spring-boot-starter-amqp 
    通过spring-rabbit来支持AMQP协议（Advanced Message Queuing Protocol）。

    4）spring-boot-starter-aop 
    支持面向方面的编程即AOP，包括spring-aop和AspectJ。

    5）spring-boot-starter-artemis 
    通过Apache Artemis支持JMS的API（Java Message Service API）。

    6）spring-boot-starter-batch 
    支持Spring Batch，包括HSQLDB数据库。

    7）spring-boot-starter-cache 
    支持Spring的Cache抽象。

    8）spring-boot-starter-cloud-connectors 
    支持Spring Cloud Connectors，简化了在像Cloud Foundry或Heroku这样的云平台上连接服务。

    9）spring-boot-starter-data-elasticsearch 
    支持ElasticSearch搜索和分析引擎，包括spring-data-elasticsearch。

    10）spring-boot-starter-data-gemfire 
    支持GemFire分布式数据存储，包括spring-data-gemfire。

    11）spring-boot-starter-data-jpa 
    支持JPA（Java Persistence API），包括spring-data-jpa、spring-orm、hibernate。

    12）spring-boot-starter-data-MongoDB 
    支持MongoDB数据，包括spring-data-mongodb。

    13）spring-boot-starter-data-rest 
    通过spring-data-rest-webmvc，支持通过REST暴露Spring Data数据仓库。

    14）spring-boot-starter-data-solr 
    支持Apache Solr搜索平台，包括spring-data-solr。

    15）spring-boot-starter-freemarker 
    支持FreeMarker模板引擎。

    16）spring-boot-starter-groovy-templates 
    支持Groovy模板引擎。

    17）spring-boot-starter-hateoas 
    通过spring-hateoas支持基于HATEOAS的RESTful Web服务。

    18）spring-boot-starter-hornetq 
    通过HornetQ支持JMS。

    19）spring-boot-starter-integration 
    支持通用的spring-integration模块。

    20）spring-boot-starter-jdbc 
    支持JDBC数据库。

    21）spring-boot-starter-jersey 
    支持Jersey RESTful Web服务框架。

    22）spring-boot-starter-jta-atomikos 
    通过Atomikos支持JTA分布式事务处理。

    23）spring-boot-starter-jta-bitronix 
    通过Bitronix支持JTA分布式事务处理。

    24）spring-boot-starter-mail 
    支持javax.mail模块。

    25）spring-boot-starter-mobile 
    支持spring-mobile。

    26）spring-boot-starter-mustache 
    支持Mustache模板引擎。

    27）spring-boot-starter-Redis 
    支持Redis键值存储数据库，包括spring-redis。

    28）spring-boot-starter-security 
    支持spring-security。

    29）spring-boot-starter-social-facebook 
    支持spring-social-facebook

    30）spring-boot-starter-social-linkedin 
    支持pring-social-linkedin

    31）spring-boot-starter-social-twitter 
    支持pring-social-twitter

    32）spring-boot-starter-test 
    支持常规的测试依赖，包括JUnit、Hamcrest、Mockito以及spring-test模块。

    33）spring-boot-starter-thymeleaf 
    支持Thymeleaf模板引擎，包括与Spring的集成。

    34）spring-boot-starter-velocity 
    支持Velocity模板引擎。

    35）spring-boot-starter-web 
    S支持全栈式Web开发，包括Tomcat和spring-webmvc。

    36）spring-boot-starter-websocket 
    支持WebSocket开发。

    37）spring-boot-starter-ws 
    支持Spring Web Services。

    Spring Boot应用启动器面向生产环境的还有2种，具体如下：

    1）spring-boot-starter-actuator 
    增加了面向产品上线相关的功能，比如测量和监控。

    2）spring-boot-starter-remote-shell 
    增加了远程ssh shell的支持。

    最后，Spring Boot应用启动器还有一些替换技术的启动器，具体如下：

    1）spring-boot-starter-jetty 
    引入了Jetty HTTP引擎（用于替换Tomcat）。

    2）spring-boot-starter-log4j 
    支持Log4J日志框架。

    3）spring-boot-starter-logging 
    引入了Spring Boot默认的日志框架Logback。

    4）spring-boot-starter-tomcat 
    引入了Spring Boot默认的HTTP引擎Tomcat。

    5）spring-boot-starter-undertow 
    引入了Undertow HTTP引擎（用于替换Tomcat）。



| Module 名称                                                  | Module 介绍                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [spring-boot-3-helloworld](./spring-boot-3-helloworld)                         | spring-boot 的一个 helloworld                                |
| [spring-boot-3-properties](./spring-boot-3-properties)                         | spring-boot 读取配置文件中的内容                             |
| [spring-boot-3-actuator](./spring-boot-3-actuator)                             | spring-boot 集成 spring-boot-starter-actuator 用于监控 spring-boot 的启动和运行状态 |
| [spring-boot-3-admin-client](./spring-boot-3-admin/admin-client)               | spring-boot 集成 spring-boot-admin 来可视化的监控 spring-boot 程序的运行状态，可以与 actuator 互相搭配使用，客户端示例 |
| [spring-boot-3-admin-server](./spring-boot-3-admin/admin-server)               | spring-boot 集成 spring-boot-admin 来可视化的监控 spring-boot 程序的运行状态，可以与 actuator 互相搭配使用，服务端示例 |
| [spring-boot-3-logback](./spring-boot-3-logback)                               | spring-boot 集成 logback 日志                                |
| [spring-boot-3-log-aop](./spring-boot-3-log-aop)                               | spring-boot 使用 AOP 切面的方式记录 web 请求日志             |
| [spring-boot-3-exception-handler](./spring-boot-3-exception-handler)           | spring-boot 统一异常处理，包括2种，第一种返回统一的 json 格式，第二种统一跳转到异常页面 |
| [spring-boot-3-template-freemarker](./spring-boot-3-template-freemarker)       | spring-boot 集成 Freemarker 模板引擎                         |
| [spring-boot-3-template-thymeleaf](./spring-boot-3-template-thymeleaf)         | spring-boot 集成 Thymeleaf 模板引擎                          |
| [spring-boot-3-template-beetl](./spring-boot-3-template-beetl)                 | spring-boot 集成 Beetl 模板引擎                              |
| [spring-boot-3-template-enjoy](./spring-boot-3-template-enjoy)                 | spring-boot 集成 Enjoy 模板引擎                              |
| [spring-boot-3-orm-jdbctemplate](./spring-boot-3-orm-jdbctemplate)             | spring-boot 集成 Jdbc Template 操作数据库，并简易封装通用 Dao 层 |
| [spring-boot-3-orm-jpa](./spring-boot-3-orm-jpa)                               | spring-boot 集成 spring-boot-starter-data-jpa 操作数据库     |
| [spring-boot-3-orm-mybatis](./spring-boot-3-orm-mybatis)                       | spring-boot 集成原生mybatis，使用 [mybatis-spring-boot-starter](https://github.com/mybatis/spring-boot-starter) 集成 |
| [spring-boot-3-orm-mybatis-mapper-page](./spring-boot-3-orm-mybatis-mapper-page) | spring-boot 集成[通用Mapper](https://github.com/abel533/Mapper)和[PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)，使用 [mapper-spring-boot-starter](https://github.com/abel533/Mapper/tree/master/spring-boot-starter) 和 [pagehelper-spring-boot-starter](https://github.com/pagehelper/pagehelper-spring-boot) 集成 |
| [spring-boot-3-orm-mybatis-plus](./spring-boot-3-orm-mybatis-plus)             | spring-boot 集成 [mybatis-plus](https://mybatis.plus/)，使用 [mybatis-plus-boot-starter](http://mp.baomidou.com/) 集成，集成 BaseMapper、BaseService、ActiveRecord 操作数据库 |
| [spring-boot-3-orm-beetlsql](./spring-boot-3-orm-beetlsql)                     | spring-boot 集成 [beetl-sql](http://ibeetl.com/guide/#beetlsql)，使用 [beetl-framework-starter](http://ibeetl.com/guide/#beetlsql) 集成 |
| [spring-boot-3-upload](./spring-boot-3-upload)                                 | spring-boot 文件上传示例，包含本地文件上传以及七牛云文件上传 |
| [spring-boot-3-cache-redis](./spring-boot-3-cache-redis)                       | spring-boot 整合 redis，操作redis中的数据，并使用redis缓存数据 |
| [spring-boot-3-cache-ehcache](./spring-boot-3-cache-ehcache)                   | spring-boot 整合 ehcache，使用 ehcache 缓存数据              |
| [spring-boot-3-email](./spring-boot-3-email)                                   | spring-boot 整合 email，包括发送简单文本邮件、HTML邮件（包括模板HTML邮件）、附件邮件、静态资源邮件 |
| [spring-boot-3-task](./spring-boot-3-task)                                     | spring-boot 快速实现定时任务                                 |
| [spring-boot-3-task-quartz](./spring-boot-3-task-quartz)                       | spring-boot 整合 quartz，并实现对定时任务的管理，包括新增定时任务，删除定时任务，暂停定时任务，恢复定时任务，修改定时任务启动时间，以及定时任务列表查询，`提供前端页面` |
| [spring-boot-3-task-xxl-job](./spring-boot-3-task-xxl-job)                     | spring-boot 整合[xxl-job](http://www.xuxueli.com/xxl-job/en/#/)，并提供绕过 `xxl-job-admin` 对定时任务的管理的方法，包括定时任务列表，触发器列表，新增定时任务，删除定时任务，停止定时任务，启动定时任务，修改定时任务，手动触发定时任务 |
| [spring-boot-3-swagger](./spring-boot-3-swagger)                               | spring-boot 集成原生的 `swagger` 用于统一管理、测试 API 接口 |
| [spring-boot-3-swagger-beauty](./spring-boot-3-swagger-beauty)                 | spring-boot 集成第三方 `swagger` [swagger-bootstrap-ui](https://github.com/xiaoymin/Swagger-Bootstrap-UI) 美化API文档样式，用于统一管理、测试 API 接口 |
| [spring-boot-3-rbac-security](./spring-boot-3-rbac-security)                   | spring-boot 集成 spring security 完成基于RBAC权限模型的权限管理，支持自定义过滤请求，动态权限认证，使用 JWT 安全认证，支持在线人数统计，手动踢出用户等操作 |
| [spring-boot-3-rbac-shiro](./spring-boot-3-rbac-shiro)                         | spring-boot 集成 shiro 实现权限管理<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-session](./spring-boot-3-session)                               | spring-boot 集成 Spring Session 实现Session共享、重启程序Session不失效 |
| [spring-boot-3-oauth](./spring-boot-3-oauth)                                   | spring-boot 实现 oauth 服务器功能，实现授权码机制<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-social](./spring-boot-3-social)                                 | spring-boot 集成第三方登录，集成 `justauth-spring-boot-starter` 实现QQ登录、GitHub登录、微信登录、谷歌登录、微软登录、小米登录、企业微信登录。 |
| [spring-boot-3-zookeeper](./spring-boot-3-zookeeper)                           | spring-boot 集成 Zookeeper 结合AOP实现分布式锁               |
| [spring-boot-3-mq-rabbitmq](./spring-boot-3-mq-rabbitmq)                       | spring-boot 集成 RabbitMQ 实现基于直接队列模式、分列模式、主题模式、延迟队列的消息发送和接收 |
| [spring-boot-3-mq-rocketmq](./spring-boot-3-mq-rocketmq)                       | spring-boot 集成 RocketMQ，实现消息的发送和接收<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-mq-kafka](./spring-boot-3-mq-kafka)                             | spring-boot 集成 kafka，实现消息的发送和接收                 |
| [spring-boot-3-websocket](./spring-boot-3-websocket)                           | spring-boot 集成 websocket，后端主动推送前端服务器运行信息   |
| [spring-boot-3-websocket-socketio](./spring-boot-3-websocket-socketio)         | spring-boot 使用 netty-socketio 集成 websocket，实现一个简单的聊天室 |
| [spring-boot-3-ureport2](./spring-boot-3-ureport2)                             | spring-boot 集成 ureport2 实现复杂的自定义的中国式报表<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-uflo](./spring-boot-3-uflo)                                     | spring-boot 集成 uflo 快速实现轻量级流程引擎<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-urule](./spring-boot-3-urule)                                   | spring-boot 集成 urule 快速实现规则引擎<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-activiti](./spring-boot-3-activiti)                             | spring-boot 集成 activiti 7  流程引擎<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-async](./spring-boot-3-async)                                   | spring-boot 使用原生提供的异步任务支持，实现异步执行任务     |
| [spring-boot-3-war](./spring-boot-3-war)                                       | spring-boot 打成 war 包的配置                                |
| [spring-boot-3-elasticsearch](./spring-boot-3-elasticsearch)                   | spring-boot 集成 ElasticSearch，集成 `spring-boot-starter-data-elasticsearch` 完成对 ElasticSearch 的高级使用技巧，包括创建索引、配置映射、删除索引、增删改查基本操作、复杂查询、高级查询、聚合查询等 |
| [spring-boot-3-dubbo](./spring-boot-3-dubbo)                                   | spring-boot 集成 Dubbo，分别为公共模块 `spring-boot-spring-boot-3-dubbo-common`、服务提供方`spring-boot-spring-boot-3-dubbo-provider`、服务调用方`spring-boot-spring-boot-3-dubbo-consumer` |
| [spring-boot-3-mongodb](./spring-boot-3-mongodb)                               | spring-boot 集成 MongoDB，使用官方的 starter 实现增删改查    |
| [spring-boot-3-neo4j](./spring-boot-3-neo4j)                                   | spring-boot 集成 Neo4j 图数据库，实现一个校园人物关系网的demo |
| [spring-boot-3-docker](./spring-boot-3-docker)                                 | spring-boot 容器化                                           |
| [spring-boot-3-multi-datasource-jpa](./spring-boot-3-multi-datasource-jpa)     | spring-boot 使用JPA集成多数据源                              |
| [spring-boot-3-multi-datasource-mybatis](./spring-boot-3-multi-datasource-mybatis) | spring-boot 使用Mybatis集成多数据源，使用 Mybatis-Plus 提供的开源解决方案实现 |
| [spring-boot-3-sharding-jdbc](./spring-boot-3-sharding-jdbc)                   | spring-boot 使用 `sharding-jdbc` 实现分库分表，同时ORM采用 Mybatis-Plus |
| [spring-boot-3-tio](./spring-boot-3-tio)                                       | spring-boot 集成 tio 网络编程框架<br /> <span style="color:pink;">待完成</span> |
| spring-boot-3-grpc                                                    | spring-boot 集成grpc，配置tls/ssl<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-codegen](./spring-boot-3-codegen)                               | spring-boot 集成 velocity 模板技术实现的代码生成器，简化开发 |
| [spring-boot-3-graylog](./spring-boot-3-graylog)                               | spring-boot 集成 graylog 实现日志统一收集                    |
| spring-boot-3-sso                                                     | spring-boot 集成 SSO 单点登录<br /> <span style="color:pink;">待完成</span> |
| [spring-boot-3-ldap](./spring-boot-3-ldap)                                     | spring-boot 集成 LDAP，集成 `spring-boot-starter-data-ldap` 完成对 Ldap 的基本 CURD操作, 并给出以登录为实战的 API 示例|
| [spring-boot-3-dynamic-datasource](./spring-boot-3-dynamic-datasource)         | spring-boot 动态添加数据源、动态切换数据源                   |
| [spring-boot-3-ratelimit-guava](./spring-boot-3-ratelimit-guava)               | spring-boot 使用 Guava RateLimiter 实现单机版限流，保护 API  |
| [spring-boot-3-ratelimit-redis](./spring-boot-3-ratelimit-redis)               | spring-boot 使用 Redis + Lua 脚本实现分布式限流，保护 API    |
| [spring-boot-3-https](./spring-boot-3-https)                                   | spring-boot 集成 HTTPS                                       |
| [spring-boot-3-elasticsearch-rest-high-level-client](./spring-boot-3-elasticsearch-rest-high-level-client) | spring boot 集成 ElasticSearch 7.x 版本，使用官方 Rest High Level Client 操作 ES 数据 |
| [spring-boot-3-flyway](./spring-boot-3-flyway)                                 | spring boot 集成 Flyway，项目启动时初始化数据库表结构，同时支持数据库脚本版本控制 |
| [spring-boot-3-ureport2](./spring-boot-3-ureport2)                             | spring boot 集成 Ureport2，实现中国式复杂报表设计            |

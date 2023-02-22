### SpringBoot之集成 Mybatis 实现双数据源



- 引入依赖

    - 添加依赖

        ```
        		<!--aop-->
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-aop</artifactId>
                </dependency>
        		<!--mybatis-->
                <dependency>
                    <groupId>org.mybatis.spring.boot</groupId>
                    <artifactId>mybatis-spring-boot-starter</artifactId>
                    <version>2.2.2</version>
                </dependency>
        
                <!--mysql-->
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <scope>runtime</scope>
                </dependency>
                <!-- Druid自动装配 -->
                <dependency>
                    <groupId>com.alibaba</groupId>
                    <artifactId>druid-spring-boot-starter</artifactId>
                    <version>1.1.21</version>
                </dependency>
        ```

        

- yml文件配置

    ```yaml
    #MyBatis
    mybatis:
      type-aliases-package: spring.boot.mapper
      mapper-locations: classpath:mapper/*.xml
    #Spring
    spring:
      datasource:
        druid:
          master:
            url: jdbc:mysql://localhost:3308/testsql?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
            username: root
            password: 123456
          slave:
            enabled: true
            url: jdbc:mysql://localhost:3308/testsqlone?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
            username: root
            password: 123456
          min-idle: 10
          initial-size: 5
          max-active: 20
          max-wait: 60000
          time-between-eviction-runs-millis: 60000
          min-evictable-idle-time-millis: 300000
          max-evictable-idle-time-millis: 900000
          validation-query: SELECT 1 FROM DUAL
          test-while-idle: true
          test-on-borrow: false
          test-on-return: false
          web-stat-filter:
            enabled: true
          stat-view-servlet:
            enabled: true
            allow:
            url-pattern: /druid/*
            login-username: admin
            login-password: 123456
          filter:
            stat:
              enabled: true
              log-slow-sql: true
              slow-sql-millis: 1000
              merge-sql: true
            wall:
              config:
                multi-statement-allow: true
          db-type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.jdbc.Driver
    
    ```

    

- 多数据源配置

    - 自定义数据源切换的注解

        ```java
        package spring.boot.config;
        
        /**
         * 自定义数据源切换的注解
         * @author yangjian
         * @date 2022/11/24 16:27
         */
        
        import java.lang.annotation.*;
        
        
        @Target({ ElementType.METHOD, ElementType.TYPE })
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        @Inherited
        public @interface DataSource {
            public DataSourceType value() default DataSourceType.MASTER;
        }
        ```

        

    - 数据源类型枚举类

        ```java
        package spring.boot.config;
        
        /**
         * 数据源类型枚举类
         * @author yangjian
         * @date 2022/11/24 16:28
         */
        public enum DataSourceType {
            /**
             * 主库
             */
            MASTER,
        
            /**
             * 从库
             */
            SLAVE
        }
        
        ```

    - 配置切面拦截

        ```java
        package spring.boot.config;
        
        
        
        import org.aspectj.lang.ProceedingJoinPoint;
        import org.aspectj.lang.annotation.Around;
        import org.aspectj.lang.annotation.Aspect;
        import org.aspectj.lang.annotation.Pointcut;
        import org.aspectj.lang.reflect.MethodSignature;
        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;
        
        import java.lang.reflect.Method;
        /**
         * 配置切面拦截
         * @author yangjian
         * @date 2022/11/24 16:32
         */
        @Aspect
        @Order(1) // 优先级
        @Component
        public class DataSourceAspect {
        
            /**
             * 通过自定义注解@DataSource定义切点
             */
            @Pointcut("@annotation(spring.boot.config.DataSource)"
                    + "|| @within(spring.boot.config.DataSource)")
            public void dsPointCut() {
            }
        
            /**
             * 切点环绕
             * @param point
             * @return
             * @throws Throwable
             */
            @Around("dsPointCut()")
            public Object around(ProceedingJoinPoint point) throws Throwable {
                DataSource dataSource = getDataSource(point);
                if (dataSource!=null) {
                    DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
                }
        
                try {
                    return point.proceed();
                } finally {
                    DynamicDataSourceContextHolder.clearDataSourceType();
                }
            }
        
            /**
             * 获取需要切换的数据源
             */
            public DataSource getDataSource(ProceedingJoinPoint point) {
                MethodSignature signature = (MethodSignature) point.getSignature();
                Class<? extends Object> targetClass = point.getTarget().getClass();
                DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
                if (targetDataSource!=null) {
                    return targetDataSource;
                } else {
                    Method method = signature.getMethod();
                    DataSource dataSource = method.getAnnotation(DataSource.class);
                    return dataSource;
                }
            }
        }
        ```

        

    - 数据源配置元数据

        ```java
        package spring.boot.config;
        
        import com.alibaba.druid.pool.DruidDataSource;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Configuration;
        
        /**
         * 数据源配置元数据
         * @author yangjian
         * @date 2022/11/24 16:35
         */
        @Configuration
        public class DruidProperties {
            @Value("${spring.datasource.druid.initial-size: 6}")
            private int initialSize;
        
            @Value("${spring.datasource.druid.min-idle}")
            private int minIdle;
        
            @Value("${spring.datasource.druid.max-active}")
            private int maxActive;
        
            @Value("${spring.datasource.druid.max-wait}")
            private int maxWait;
        
            @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
            private int timeBetweenEvictionRunsMillis;
        
            @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
            private int minEvictableIdleTimeMillis;
        
            @Value("${spring.datasource.druid.max-evictable-idle-time-millis}")
            private int maxEvictableIdleTimeMillis;
        
            @Value("${spring.datasource.druid.validation-query}")
            private String validationQuery;
        
            @Value("${spring.datasource.druid.test-while-idle}")
            private boolean testWhileIdle;
        
            @Value("${spring.datasource.druid.test-on-borrow}")
            private boolean testOnBorrow;
        
            @Value("${spring.datasource.druid.test-on-return}")
            private boolean testOnReturn;
        
            public DruidDataSource dataSource(DruidDataSource datasource) {
                /** 配置初始化大小、最小、最大 */
                datasource.setInitialSize(initialSize);
                datasource.setMaxActive(maxActive);
                datasource.setMinIdle(minIdle);
        
                /** 配置获取连接等待超时的时间 */
                datasource.setMaxWait(maxWait);
        
                /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
                datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        
                /** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
                datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
                datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        
                /**
                 * 用来检测连接是否有效的sql，要求是一个查询语句，常用select
                 * 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
                 */
                datasource.setValidationQuery(validationQuery);
                /**
                 * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
                 */
                datasource.setTestWhileIdle(testWhileIdle);
                /** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
                datasource.setTestOnBorrow(testOnBorrow);
                /** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
                datasource.setTestOnReturn(testOnReturn);
                return datasource;
            }
        }
        ```

    - 数据源配置

        ```java
        package spring.boot.config;
        
        import com.alibaba.druid.pool.DruidDataSource;
        import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
        import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
        import com.alibaba.druid.util.Utils;
        import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
        import org.springframework.boot.context.properties.ConfigurationProperties;
        import org.springframework.boot.web.servlet.FilterRegistrationBean;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Primary;
        
        import javax.servlet.*;
        import javax.sql.DataSource;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;
        
        /**
         * 数据源配置
         * @author yangjian
         * @date 2022/11/24 16:36
         */
        @Configuration
        public class DruidConfig {
        
            @Bean
            @ConfigurationProperties("spring.datasource.druid.master")
            public DataSource masterDataSource(DruidProperties druidProperties) {
                DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
                return druidProperties.dataSource(dataSource);
            }
        
            @Bean
            @ConfigurationProperties("spring.datasource.druid.slave")
            @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
            public DataSource slaveDataSource(DruidProperties druidProperties) {
                DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
                return druidProperties.dataSource(dataSource);
            }
        
            @Bean(name = "dynamicDataSource")
            @Primary
            public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
                Map<Object, Object> targetDataSources = new HashMap<>();
                targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
                targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
                return new DynamicDataSource(masterDataSource, targetDataSources);
            }
        
            /**
             * 去除监控页面底部广告
             * @param properties
             * @return
             */
            @SuppressWarnings({"rawtypes", "unchecked"})
            @Bean
            @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
            public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
                //获取web监控页面的参数
                DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
                //提取common.js的配置路径
                String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
                String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
                final String filePath = "support/http/resources/js/common.js";
                //创建filter进行过滤
                Filter filter = new Filter() {
        
                    @Override
                    public void init(FilterConfig filterConfig) throws ServletException {
        
                    }
        
                    @Override
                    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                            throws IOException, ServletException {
                        chain.doFilter(request, response);
                        //重置缓冲区，响应头不会被重置
                        response.resetBuffer();
                        //获取common.js
                        String text = Utils.readFromResource(filePath);
                        //正则替换banner, 除去底部广告信息
                        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                        text = text.replaceAll("powered.*?shrek.wang</a>", "");
                        response.getWriter().write(text);
                    }
        
                    @Override
                    public void destroy() {
        
                    }
                };
        
                FilterRegistrationBean registrationBean = new FilterRegistrationBean();
                registrationBean.setFilter(filter);
                registrationBean.addUrlPatterns(commonJsPattern);
                return registrationBean;
            }
        }
        ```

    - 动态数据源切换上下文

        ```java
        package spring.boot.config;
        
        import lombok.extern.slf4j.Slf4j;
        
        /**
         * 动态数据源切换上下文
         * @author yangjian
         * @date 2022/11/24 16:37
         */
        @Slf4j
        public class DynamicDataSourceContextHolder {
            private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
        
            public static void setDataSourceType(String dataSourceType) {
                log.info("切换到{}数据库",dataSourceType);
                CONTEXT_HOLDER.set(dataSourceType);
            }
        
            public static String getDataSourceType() {
                return CONTEXT_HOLDER.get();
            }
        
            public static void clearDataSourceType() {
                CONTEXT_HOLDER.remove();
            }
        }
        ```

        

    - 动态数据源处理

        ```java
        package spring.boot.config;
        
        import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
        
        import javax.sql.DataSource;
        import java.util.Map;
        
        /**
         * 动态数据源处理
         * @author yangjian
         * @date 2022/11/24 16:37
         */
        
        public class DynamicDataSource extends AbstractRoutingDataSource {
            @Override
            protected Object determineCurrentLookupKey() {
                return DynamicDataSourceContextHolder.getDataSourceType();
            }
        
            public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
                super.setDefaultTargetDataSource(defaultTargetDataSource);
                super.setTargetDataSources(targetDataSources);
                super.afterPropertiesSet();
            }
        }
        ```

    - x

        

- 测试代码

    - 创建UserMapper.xml

        路径：src/main/resources/mapper/UserMapper.xml

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="spring.boot.mapper.UserMapper">
            <select id="getList" resultType="spring.boot.entity.User">
                select id,username,age from user
            </select>
            <select id="getList2" resultType="spring.boot.entity.User">
                select id,username,age from user
            </select>
        
        </mapper>
        ```

        

    - 创建实体类User

        ```java
        package spring.boot.entity;
        
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        
        /**
         * @author yangjian
         * @date 2022/11/24 17:07
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class User {
        
            private int id;
            private String username;
            private int age;
        
        }
        
        
        ```

        

    - 创建UserMapper

        ```java
        package spring.boot.mapper;
        
        import org.springframework.stereotype.Repository;
        import spring.boot.entity.User;
        
        import java.util.List;
        
        /**
         * @author yangjian
         * @date 2022/11/24 16:50
         */
        
        @Repository
        public interface UserMapper {
            List<User> getList();
            List<User> getList2();
        }
        
        ```

        

    - 创建service

        ```java
        package spring.boot.service;
        
        import spring.boot.entity.User;
        
        import java.util.List;
        
        /**
         * @author yangjian
         * @date 2022/11/24 17:17
         */
        public interface UserService {
            List<User> getList();
            List<User> getList2();
        }
        
        
        package spring.boot.service.impl;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import spring.boot.config.DataSource;
        import spring.boot.config.DataSourceType;
        import spring.boot.entity.User;
        import spring.boot.mapper.UserMapper;
        import spring.boot.service.UserService;
        
        import java.util.List;
        
        /**
         * @author yangjian
         * @date 2022/11/24 17:18
         */
        @Service
        public class UserServiceImpl implements UserService {
            @Autowired
            private UserMapper userMapper;
            @Override
            public List<User> getList() {
                return userMapper.getList();
            }
        
            /**
             * 配置访问从库
             * @return
             */
            @Override
            @DataSource(DataSourceType.SLAVE)
            public List<User> getList2() {
                return userMapper.getList2();
            }
        }
        
        ```

        

    - 创建UserController

        ```java
        package spring.boot.controller;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;
        import spring.boot.entity.User;
        import spring.boot.service.UserService;
        
        import java.util.List;
        
        /**
         * @author yangjian
         * @date 2022/11/24 17:22
         */
        @RestController
        public class UserController {
            @Autowired
            private UserService userService;
        
            @GetMapping("/get")
            public List<User> getList(){
                return userService.getList();
            }
        
            @GetMapping("/get2")
            public List<User> getList2(){
                return userService.getList2();
            }
        }
        
        ```

    - 启动类

        ```java
        package spring.boot;
        
        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        
        @SpringBootApplication
        @MapperScan(basePackages = "spring.boot.mapper")
        public class SpringBootMybatisApplication {
        
            public static void main(String[] args) {
                SpringApplication.run(SpringBootMybatisApplication.class, args);
            }
        
        }
        
        ```

        

- 测试

​	数据库：testsql   

<img src="image/SpringBoot之集成 Mybatis 实现双数据源.assets/image-20221124183135488.png" alt="image-20221124183135488" style="zoom:80%;" />

​	浏览器访问：http://localhost:8080/get

<img src="image/SpringBoot之集成 Mybatis 实现双数据源.assets/image-20221124183314394.png" alt="image-20221124183314394" style="zoom:80%;" />

 	数据库：testsqlone  

<img src="image/SpringBoot之集成 Mybatis 实现双数据源.assets/image-20221124183207565.png" alt="image-20221124183207565" style="zoom:80%;" />

​	  浏览器访问：http://localhost:8080/get2

<img src="image/SpringBoot之集成 Mybatis 实现双数据源.assets/image-20221124183336561.png" alt="image-20221124183336561" style="zoom:80%;" />
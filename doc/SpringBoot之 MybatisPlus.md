### SpringBoot之 MybatisPlus

pom

```xml
		<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.3.1</version>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>	
```

yml

```properties
# 应用名称
spring.application.name=spring-boot-mybatis-plus
# 应用服务 WEB 访问端口
server.port=8080
# 数据库驱动：
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# 数据源名称
spring.datasource.name=defaultDataSource
# 数据库连接地址
spring.datasource.url=jdbc:mysql://localhost:3308/testsql?serverTimezone=UTC
# 数据库用户名&密码：
spring.datasource.username=root
spring.datasource.password=123456

mybatis-plus.type-aliases-package=spring.boot.mybatisplus.entity
mybatis-plus.mapper-locations=classpath:mapper/*.xml



```

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="spring.boot.mybatisplus.mapper.UserMapper">

    <resultMap id="BaseResultMap" type="User">
        <id property="id" column="id" jdbcType="INTEGER"/>
        <result property="username" column="username" jdbcType="VARCHAR"/>
        <result property="age" column="age" jdbcType="INTEGER"/>
    </resultMap>

    <sql id="Base_Column_List">
        id,username,age
    </sql>

    <select id="selectByUsername" parameterType="string" resultMap="BaseResultMap">
        select
            <include refid="Base_Column_List"/>
        from user
        where username = #{username}
    </select>


</mapper>

```

启动类

```java
package spring.boot.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("spring.boot.mybatisplus.mapper")
public class SpringBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
    }

}

```

实体类

```java
package spring.boot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String username;

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private Integer age;
}

```

mapper

```java
package spring.boot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import spring.boot.mybatisplus.entity.User;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}

```

service 

```java
package spring.boot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import spring.boot.mybatisplus.entity.User;

public interface UserService  extends IService<User> {
    User selectByUsername(String username);
}

```

serviceImpl

```java
package spring.boot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.mybatisplus.entity.User;
import spring.boot.mybatisplus.mapper.UserMapper;
import spring.boot.mybatisplus.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }


}

```

controller

```java
package spring.boot.mybatisplus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mybatisplus.entity.User;
import spring.boot.mybatisplus.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     *
     * @return
     */
    @GetMapping("/user/info")
    public User selectByUsername(@RequestParam("username") String username){
        System.out.println("usrname::::"+username);
        return userService.selectByUsername(username);

    }
}

```


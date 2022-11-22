### SpringBoot之多种依赖方式

#### Parent POM 方式

继承spring-boot-starter-parent项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

使用 Parent POM 方式，我们可以单独覆盖依赖包的版本:

```xml
<properties>
    <java.version>1.8</java.version>
    <lombok.version>1.18.24</lombok.version>
</properties>
```



#### import 方式

导入spring-boot-dependencies项目依赖

Spring Boot 提供了`spring-boot-dependencies`包，`spring-boot-starter-parent`就是继承`spring-boot-dependencies`实现的。

我们可通过使用 import 的方式来集成 Spring Boot 包管理。Import 的方式可以是我们的项目有机会继承其他的 parent pom。

使用这种方式，我们就不能通过使用 property 参数的方式去覆盖我们的依赖包版本。如果我们想要覆盖某一个 jar 的版本，需要将其在dependencyManagement中声明，并添加：

```xml
<properties>
        <java.version>8</java.version>
        <spring.boot.version>2.6.11</spring.boot.version>
    </properties>
<dependencyManagement>
        <dependencies>
           <!-- 修改版本时 放此位置： 防在spring-boot-dependencies之前-->
           <!-- springboot -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring.boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

注意：要修改Spring Boot的依赖组件版本可能会造成不兼容的问题。

#### 资源文件过滤问题

使用继承Spring Boot时，如果要使用Maven resource filter过滤资源文件时，资源文件里面的占位符为了使${}和Spring Boot区别开来，此时要用@...@包起来，不然无效。另外，@...@占位符在yaml文件编辑器中编译报错，所以使用继承方式有诸多问题，坑要慢慢趟。
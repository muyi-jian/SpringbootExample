### SpringBoot之目录结构介绍

Spring Boot 和传统项目最大的区别：

1. 传统项目都是打成 WAR 包部署到服务器上面，需要额外的 Servlet 容器
2. Spring Boot 则可以直接打成 jar 包，并内置集成了 Servlet 容器，通过命令 `java -jar xx.jar` 可以直接运行

打成可执行 jar 包后，我们来看下其中的 `META-INF/MANIFEST.MF` 文件

```
Manifest-Version: 1.0
Spring-Boot-Classpath-Index: BOOT-INF/classpath.idx
Implementation-Title: spring-boot-helloworld
Implementation-Version: 0.0.1-SNAPSHOT
Spring-Boot-Layers-Index: BOOT-INF/layers.idx
Start-Class: com.springboot.SpringBootHelloworldApplication
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Build-Jdk-Spec: 1.8
Spring-Boot-Version: 2.7.5
Created-By: Maven JAR Plugin 3.2.2
Main-Class: org.springframework.boot.loader.JarLauncher

```

`Start-Class` 便是这个 jar 包的入口类，这个入口类推荐是放在一个项目的顶层包中，其他所有的类都放在其子包下面，目录结构如以下所示。

```
spring
 +- boot
     +- SpringBootHelloworldApplication.java
     |
     +- Controller
     |   +- HelloWorldController.java
     |
     +- XX
         +- XX.java
```

这个目录结构是主流及推荐的做法，而在主入口类上加上 **@SpringBootApplication** 注解来开启 Spring Boot 的各项能力，如自动配置、组件扫描等。

```JAVA
package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloworldApplication.class, args);
    }

}

```


### SpringBoot之Runner

Spring Boot启动的时候想要运行一些特定的代码，可以实现接口 `ApplicationRunner`或者 `CommandLineRunner`，这两个接口实现方式一样，它们都只提供了一个run方法。

#### CommandLineRunner

启动获取命令行参数。

```java
package org.springframework.boot;

@FunctionalInterface
public interface CommandLineRunner {
    void run(String... args) throws Exception;
}

```

#### ApplicationRunner

启动获取应用启动的时候参数。

```java

package org.springframework.boot;

@FunctionalInterface
public interface ApplicationRunner {
    void run(ApplicationArguments args) throws Exception;
}

```

#### 使用方式

```java
@Component
public class MyBean implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
```

或者

```java
 @Bean
 public CommandLineRunner init(){
     return(String...strings)-> { };
 }
```

当项目启动存在多个ApplicationRunner和CommandLineRunner，想控制它们的启动顺序，可以实现 ：

- org.springframework.core.Ordered 接口

- org.springframework.core.annotation.Order 注解。


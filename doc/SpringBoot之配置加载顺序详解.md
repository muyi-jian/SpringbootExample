### SpringBoot之配置加载顺序详解

 Spring Boot 会涉及到很多配置文件，那么配置加载的优先级是怎么样的呢？

**配置属性加载的顺序如下：加载的优先级从高到低**

```
1、开发者工具 `Devtools` 全局配置参数；

2、单元测试上的 `@TestPropertySource` 注解指定的参数；

3、单元测试上的 `@SpringBootTest` 注解指定的参数；

4、命令行指定的参数，如 `java -jar springboot.jar --name="Java技术栈"`；

5、命令行中的 `SPRING_APPLICATION_JSONJSON` 指定参数, 如 `java -Dspring.application.json='{"name":"Java技术栈"}' -jar springboot.jar`

6、`ServletConfig` 初始化参数；

7、`ServletContext` 初始化参数；

8、JNDI参数（如 `java:comp/env/spring.application.json`）；

9、Java系统参数（来源：`System.getProperties()`）；

10、操作系统环境变量参数；

11、`RandomValuePropertySource` 随机数，仅匹配：`ramdom.*`；

12、JAR包外面的配置文件参数（`application-{profile}.properties（YAML）`）

13、JAR包里面的配置文件参数（`application-{profile}.properties（YAML）`）

14、JAR包外面的配置文件参数（`application.properties（YAML）`）

15、JAR包里面的配置文件参数（`application.properties（YAML）`）

16、`@Configuration`配置文件上 `@PropertySource` 注解加载的参数；

17、默认参数（通过 `SpringApplication.setDefaultProperties` 指定）；
```

**springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文
件**
–file:./config/
–file:./
–classpath:/config/
–classpath:/
优先级由高到底，高优先级的配置会覆盖低优先级的配置；

SpringBoot会从这四个位置全部加载主配置文件；互补配置；

==我们还可以通过spring.config.location来改变默认的配置文件位置==
项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；指定配置文件和默
认加载的这些配置文件共同起作用形成互补配置；
java -jar spring-boot-helloworld-0.0.1-SNAPSHOT.jar --spring.config.location=D:/application.properties

**测试优先级**

1、在主应用程序中添加 Java 系统参数。

```
@Bean
public CommandLineRunner commandLineRunner() {
    return (args) -> {
        System.setProperty("name", "javastack-system-properties");
    };
}
```

2、在 application.properties 文件中添加属性。

```
name = javastack-application
```

3、在 application-dev.properties 文件中添加属性。

```
name = javastack-application-dev
```

4、添加测试类

```
@RunWith(SpringRunner.class)
@SpringBootTest(value = { "name=javastack-test", "sex=1" })
@ActiveProfiles("dev")
public class SpringBootBestPracticeApplicationTests {

    @Value("${name}")
    private String name;

    @Test
    public void test() {
        System.out.println("name is " + name);
    }

}
```

运行 test 单元测试，程序输出：

```
name is javastack-test
```

根据以上参数动态调整，发现参数会被正确被覆盖。了解了 Spring Boot 各种配置的加载顺序，如果配置被覆盖了我们就知道是什么问题了。
### Springboot核心配置文件详解

SpringBoot中有两种配置文件,Spring Boot会自动加载classpath目前下的这两个文件，文件格式为properties或者yml格式。

- bootstrap (支持.yml 或者 .properties)
- application (支持.yml 或者 .properties)

#### bootstrap/ application 的区别

- 加载顺序上

    bootstrap（.yml 或者 .properties）先加载
    application（.yml 或者 .properties）后加载

    bootstrap用于应用程序上下文的引导阶段。

    bootstrap由父Spring ApplicationContext加载。

    父ApplicationContext 被加载到使用 application.yml 的之前。

    boostrap 里面的属性不能被覆盖

- 配置区别

    bootstrap.yml 和application.yml 都可以用来配置参数。

    bootstrap.yml 可以理解成系统级别的一些参数配置，用来加载外部配置，如配置中心的配置信息，也可以用来定义系统不会变化的属性。

    - ​	使用 Spring Cloud Config 配置中心时，这时需要在 bootstrap 配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息；
    - 一些固定的不能被覆盖的属性
    - 一些加密/解密的场景；

    application.yml 可以用来定义应用级别的，是当前应用的配置文件。主要用于 Spring Boot 项目的自动化配置，如果搭配 spring-cloud-config 使用 application.yml 里面定义的文件可以实现动态替换。

#### yml和properties

- .properties文件是key=value的形式

- .yml是key: value的形式

    k:(空格)v：表示一对键值对（空格必须有）；
    以空格的缩进来控制层级关系；只要是左对齐的一列数据，都是同一个层级的

    属性和值也是大小写敏感

    - 值的写法

        字面量：普通的值（数字，字符串，布尔）
        k: v：字面直接来写；
        字符串默认不用加上单引号或者双引号；
        ""：双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
        name: "zhangsan \n lisi"：输出；zhangsan 换行 lisi
        ''：单引号；会转义特殊字符，特殊字符最终只是一个普通的字符串数据
        name: ‘zhangsan \n lisi’：输出；zhangsan \n lisi
        对象、Map（属性和值）（键值对）：
        k: v：在下一行来写对象的属性和值的关系；注意缩进
        对象还是k: v的方式

        ```yaml
        friends:
        	lastName: zhangsan        
        	age: 20 
        ```

        行内写法：

        ```yaml
        friends: {lastName: zhangsan,age: 18}
        ```

        数组（List、Set）：
        用- 值表示数组中的一个元素

        ```yaml
        pets:
         ‐ cat
         ‐ dog
         ‐ pig
        ```

        行内写法

        ```yaml
        pets: [cat,dog,pig]
        ```

        3、配置文件值注入
        配置文件
        javaBean：
        我们可以导入配置文件处理器，以后编写配置就有提示了
        pets: [cat,dog,pig]

    - x

- .yml加载的属性是有顺序的，但不支持@PropertySource注解来导入配置，一般推荐用yml文件，看下来更加形象。

#### 外部配置加载顺序

==SpringBoot也可以从以下位置加载配置； 优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会
形成互补配置==

1. 命令行参数

    所有的配置都可以在命令行上进行指定

    java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --server.port=8087 --server.context-path=/abc

    多个配置用空格分开； --配置项=值

2. 来自java:comp/env的JNDI属性

3. Java系统属性（System.getProperties()）

4. 操作系统环境变量

5. RandomValuePropertySource配置的random.*属性值

==由jar包外向jar包内进行寻找；==

==优先加载带profile==

1. jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件
2. jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件

==再来加载不带profile==

1. jar包外部的application.properties或application.yml(不带spring.profile)配置文件
2. jar包内部的application.properties或application.yml(不带spring.profile)配置文件
3. @Configuration注解类上的@PropertySource
4. 通过SpringApplication.setDefaultProperties指定的默认属性

#### 配置文件占位符

**RandomValuePropertySource**:配置文件可以使用[随机数](https://so.csdn.net/so/search?q=随机数&spm=1001.2101.3001.7020)属性配置占位符：可以在配置文件中引用前面配置过的属性（优先级前面配置过的这里都能用）

**随机数**：

```
${random.value} ${random.int} ${random.long} ${random.int(10)} ${random.int[12543.26]} 
person.age=${random.int}
person.name=成龙${random.uuid}
```

**占位符获取之前配置的值，如果没有可以是用:指定默认值**

```
1.没有指定默认值
    person.dog.name=${person.hello}_dog
    输出：${person.hello}_dog
1.指定默认值
    person.dog.name=${person.hello:hello}_dog
    输出：hello_dog
```


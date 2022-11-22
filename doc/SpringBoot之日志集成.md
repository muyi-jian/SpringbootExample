### SpringBoot之日志集成

#### 什么是日志

程序运行过程中所产生的信息就是日志，通过日志查看程序的运行过程，运行信息，异常信息等

当我们启动SpringBoot时，控制台打印的信息就是日志

![image-20221122200148677](F:\学习笔记\Notes\attachment\SpringBoot之日志集成.assets\image-20221122200148677.png)

#### 日志的作用

- 发现和定位问题
- 记录用户的登录信息，进行大数据分析
- 记录系统的操作信息，方便数据的恢复和定位操作者
- 记录程序的执行时间，方便以后优化程序

#### 日志等级

日志记录器（Logger）的行为是分等级的。如下表所示：

分为：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF



所有日志级别顺序为：**ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF**
比如：选择级别为info,则只会显示 INFO、WARN、ERROR、FATAL、OFF这几个级别的日志，小于该级别的日志都不会进行显示。

默认情况下，spring boot从控制台打印出来的日志级别只有INFO及以上级别，可以配置日志级别

```yaml
# 设置日志级别
logging:
  level:
    root: ERROR

```



#### 日志框架

市面上的日志框架；
JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j...

SpringBoot：底层是Spring框架，Spring框架默认是用JCL；‘
SpringBoot选用 SLF4j和logback；

如果使用starters启动器，Spring Boot将使用Logback作为默认日志框架。无论使用哪种日志框架，Spring Boot都支持配置将日志输出到控制台或者文件中。

spring-boot-starter启动器包含spring-boot-starter-logging启动器并集成了slf4j日志抽象及Logback日志框架。

#### 日志配置

根据不同的日志框架，默认加载的日志配置文件的文件名，放在资源根目录下，其他的目录及文件名不能被加载。

| Logging System          | Customization                                                |
| :---------------------- | :----------------------------------------------------------- |
| Logback                 | logback-spring.xml, logback-spring.groovy, logback.xml or logback.groovy |
| Log4j2                  | log4j2-spring.xml or log4j2.xml                              |
| JDK (Java Util Logging) | logging.properties                                           |

默认自带了Logback框架，资源目录下创建一个logback-spring.xml （默认日志文件的名字）即可，下面是一个配置文件参考：

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--
根节点<configuration>，包含下面三个属性：
scan: 当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod: 设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug: 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration>
    <!--
    子节点<property> ：用来定义变量值，它有两个属性name和value，通过<property>定义的值会被插入到logger上下文中，可以使“${}”来使用变量。
    -->
    <property name="APP_NAME" value="jobmd-extra-service"/>
    <property name="LOG_HOME" value="logs"/>
    <!--
     子节点<timestamp>：获取时间戳字符串，他有两个属性key和datePattern
    　　key: 标识此<timestamp> 的名字；
    　　datePattern: 设置将当前时间（解析配置文件的时间）转换为字符串的模式，遵循java.txt.SimpleDateFormat的格式。
     -->
    <timestamp key="TIMESTAMP" datePattern="yyyyMMddHHmmss"/>
    <!--
     子节点<contextName>：用来设置上下文名称，每个logger都关联到logger上下文，默认上下文名称为default。
     但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
     -->
    <contextName>${APP_NAME}-${TIMESTAMP}</contextName>
    <!-- 日志输入到命令行的appender定义

     子节点<appender>：负责写日志的组件，它有两个必要属性name和class; name指定appender名称，class指定appender的全限定名
       ConsoleAppender 把日志输出到控制台，有以下子节点：
    　　  <encoder>：对日志进行格式化。
    　　  <target>：字符串System.out(默认)或者System.err
     -->
    <appender name="CONSOLE-LOG" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>[%d{yyyy-MM-dd' 'HH:mm:ss.sss}] [%C] [%t] [%L] [%-5p] %m%n</pattern>
        </layout>
    </appender>

    <!--获取比info级别高(包括info级别)但除error级别的日志

    RollingFileAppender：滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件。有以下子节点：
      <file>：被写入的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建，没有默认值。
      <append>：如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true。
      <rollingPolicy>:当发生滚动时，决定RollingFileAppender的行为，涉及文件移动和重命名。属性class定义具体的滚动策略类
        class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。有以下子节点：
        <fileNamePattern>：必要节点，包含文件名及“%d”转换符，“%d”可以包含一个java.text.SimpleDateFormat指定的时间格式，
          如：%d{yyyy-MM}。如果直接使用 %d，默认格式是 yyyy-MM-dd。RollingFileAppender的file字节点可有可无，
          通过设置file，可以为活动文件和归档文件指定不同位置，当前日志总是记录到file指定的文件（活动文件），活动文件的
          名字不会改变；如果没设置file，活动文件的名字会根据fileNamePattern 的值，每隔一段时间改变一次。“/”或者“\”
          会被当做目录分隔符。
        <maxHistory>: 可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每个月滚动，
          且<maxHistory>是6，则只保存最近6个月的文件，删除之前的旧文件。注意，删除旧文件是，那些为了归档而创建的目录也会被删除。
          class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy"： 查看当前活动文件的大小，
          如果超过指定大小会告知RollingFileAppender 触发当前活动文件滚动。只有一个节点:
  　　　 <maxFileSize>:这是活动文件的大小，默认值是10MB。
  　　　 <prudent>：当为true时，不支持FixedWindowRollingPolicy。支持TimeBasedRollingPolicy，但是有两个限制，
          1不支持也不允许文件压缩，2不能设置file属性，必须留空。
        <triggeringPolicy >: 告知 RollingFileAppender 合适激活滚动。
  　　　   class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy" 根据固定窗口算法重命名文件的滚动策略。有以下子节点：
  　　　　　<minIndex>:窗口索引最小值
  　　　　　<maxIndex>:窗口索引最大值，当用户指定的窗口过大时，会自动将窗口设置为12。
  　　　　　<fileNamePattern>:必须包含“%i”例如，假设最小值和最大值分别为1和2，命名模式为 mylog%i.log,会产生
            归档文件mylog1.log和mylog2.log。还可以指定文件压缩选项，例如，mylog%i.log.gz 或者 没有log%i.log.zip
    -->
    <appender name="INFO-LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 过滤掉error级别日志，其他级别的日志策略为ACCEPT -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>DENY</onMatch>
            <onMismatch>ACCEPT</onMismatch>
        </filter>
        <encoder>
            <pattern>[%d{yyyy-MM-dd' 'HH:mm:ss.sss}] [%C] [%t] [%L] [%-5p] %m%n</pattern>
        </encoder>
        <!--滚动策略-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}//%d-info.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <!-- 输出error级别的日志 -->
    <appender name="ERROR-LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 只接受error级别日志 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <encoder>
            <pattern>[%d{yyyy-MM-dd' 'HH:mm:ss.sss}] [%C] [%t] [%L] [%-5p] %m%n</pattern>
        </encoder>
        <!--滚动策略-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}//%d-error.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <!--
      FileAppender：把日志添加到文件，有以下子节点：
    　  <file>：被写入的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建，没有默认值。
    　  <append>：如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true。
    　　<encoder>：对记录事件进行格式化。（具体参数稍后讲解 ）
    　　<prudent>：如果是 true，日志会被安全的写入文件，即使其他的FileAppender也在向此文件做写入操作，效率低，默认是 false。
      -->
    <appender name="File" class="ch.qos.logback.core.FileAppender">
        <file>${LOG_HOME}/${appName}.file.log</file>
        <append>true</append>　
        <encoder>
            <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 异步输出 -->
    <appender name="ASYNC-INFO" class="ch.qos.logback.classic.AsyncAppender">
        <!-- 不丢失日志.默认的,如果队列的80%已满,则会丢弃TRACT、DEBUG、INFO级别的日志 -->
        <discardingThreshold>0</discardingThreshold>
        <!-- 更改默认的队列的深度,该值会影响性能.默认值为256 -->
        <queueSize>256</queueSize>
        <!-- 添加附加的appender,最多只能添加一个 -->
        <appender-ref ref="INFO-LOG"/>
    </appender>

    <appender name="ASYNC-ERROR" class="ch.qos.logback.classic.AsyncAppender">
        <!-- 不丢失日志.默认的,如果队列的80%已满,则会丢弃TRACT、DEBUG、INFO级别的日志 -->
        <discardingThreshold>0</discardingThreshold>
        <!-- 更改默认的队列的深度,该值会影响性能.默认值为256 -->
        <queueSize>256</queueSize>
        <!-- 添加附加的appender,最多只能添加一个 -->
        <appender-ref ref="ERROR-LOG"/>
    </appender>

    <!--
    子节点<loger>：用来设置某一个包或具体的某一个类的日志打印级别、以及指定<appender>。<loger>仅有一个name属性，一个可选的level和一个可选的addtivity属性。可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个loger
      name: 用来指定受此loger约束的某一个包或者具体的某一个类。
      level: 用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL和OFF，还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。 如果未设置此属性，那么当前loger将会继承上级的级别。
      addtivity: 是否向上级loger传递打印信息。默认是true。同<loger>一样，可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个loger。
    -->
    <!--<logger name="com.zjw" level="INFO" addtivity="false"/>-->

    <!--
    子节点<root>:它也是<loger>元素，但是它是根loger,是所有<loger>的上级。只有一个level属性，因为name已经被命名为"root",且已经是最上级了。
  　　　　level: 用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL和OFF，不能设置为INHERITED或者同义词NULL。 默认是DEBUG。
    -->
    <root level="info">
        <appender-ref ref="CONSOLE-LOG"/>
        <appender-ref ref="ASYNC-INFO"/>
        <appender-ref ref="ASYNC-ERROR"/>
    </root>
</configuration>

```

推荐使用logback-spring.xml作为文件名，因为logback.xml加载太早

**测试**

```java
package spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @date 2022/11/22 20:19
 */
@RestController
public class LogController {
    private Logger log = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/testLog")
    public String testLog(String msg) {
        log.info("这是info信息：{}", msg);
        log.debug("这是debug信息：{}", msg);
        log.warn("这是warn信息：{}", msg);
        log.error("这是error信息：{}", msg);
        return "success";
    }
}


```

可以看到控制台：

![image-20221122202523293](F:\学习笔记\Notes\attachment\SpringBoot之日志集成.assets\image-20221122202523293.png)



#### 整合使用Log4j2框架

看了上面我们知道Springboot默认支持的是Logback框架，但是如果我们要使用Log4j2可以不呢，其实也是可以的，不过我们使用Log4j2框架之前需要先将SpringBoot框架中Logback的所有实现和spring中自带的日志框架全部去掉，然后替换成Log4j2即可，操作步骤如下：

##### 修改pom.xml文件，移除多余的Jar包并添加新的jar包
移除springboot中的默认日志框架：spring-boot-starter-logging
引入log4j2框架jar包：spring-boot-starter-log4j2
移除spring中的jar包：commons-logging

**修改之前**：

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
 </dependencies>
```

**修改之后**：

```
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--引入log4j2框架jar包-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

    </dependencies>
```

##### 添加log4j2.xml配置文件(和application.properties平级)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- <configuration status="OFF" packages="com.xyebank.hzx.core.log4j2"> -->
<configuration status="OFF">
    <properties>
        <!-- 当前日志文件路径 -->
        <property name="fileSavePath" value="/home/logs/sweep/sweep(${hostName}).log"/>
        <property name="errorFileSavePath" value="/home/logs/sweep/sweep(${hostName})-error.log"/>
        <!-- 历史日志文件路径 -->
        <property name="fileBackSavePath" value="/home/backlogs/sweep"/>
        <!-- 日志级别 -->
        <property name="logLevel" value="debug"/>
        <property name="layoutPattern"
                  value="%d{yyyy-MM-dd HH:mm:ss.SSS}---[%-5level]---[%-32X{traceId}]---[%-16.16thread]---[%c{64}] : %msg%n"/>
    </properties>
    <!-- 日志主配置 -->
    <appenders>
        <!-- 控制台 -->
        <Console name="Console" target="SYSTEM_OUT">
            <!-- <SensitiveDataPatternLayout pattern="${layoutPattern}" /> -->
            <PatternLayout pattern="${layoutPattern}"/>
        </Console>
        <!-- 文件 -->
        <RollingRandomAccessFile name="RollingRandomAccessFile" fileName="${fileSavePath}"
                                 filePattern="${fileBackSavePath}-%d{yyyy-MM-dd}.%i.log.zip">
            <!-- <SensitiveDataPatternLayout pattern="${layoutPattern}" /> -->
            <PatternLayout pattern="${layoutPattern}"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="200MB"/>
            </Policies>
            <DefaultRolloverStrategy max="30"/>
        </RollingRandomAccessFile>
        <!-- 错误日志 -->
        <RollingRandomAccessFile name="ERROR" fileName="${errorFileSavePath}"
                                 filePattern="${fileBackSavePath}-error-%d{yyyy-MM-dd}.%i.log.zip">
            <!-- <SensitiveDataPatternLayout pattern="${layoutPattern}" /> -->
            <PatternLayout pattern="${layoutPattern}"/>
            <Filters>
                <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
            <Policies>
                <SizeBasedTriggeringPolicy size="200MB"/>
            </Policies>
            <DefaultRolloverStrategy max="30"/>
        </RollingRandomAccessFile>
    </appenders>

    <!--过滤掉部分组件的其余错误信息-->
    <loggers>
        <!--        <AsyncLogger name="org.apache.zookeeper" level="error" additivity="false">-->
        <!--            <appender-ref ref="RollingRandomAccessFile" />-->
        <!--            <appender-ref ref="ERROR" />-->
        <!--            <appender-ref ref="Console" />-->
        <!--        </AsyncLogger>-->
        <!--        <AsyncLogger name="com.alibaba.dubbo" level="error" additivity="false">-->
        <!--            <appender-ref ref="RollingRandomAccessFile" />-->
        <!--            <appender-ref ref="ERROR" />-->
        <!--            <appender-ref ref="Console" />-->
        <!--        </AsyncLogger>-->
        <!--        <AsyncLogger name="org.I0Itec.zkclient" level="error" additivity="false">-->
        <!--            <appender-ref ref="RollingRandomAccessFile" />-->
        <!--            <appender-ref ref="ERROR" />-->
        <!--            <appender-ref ref="Console" />-->
        <!--        </AsyncLogger>-->
        <!--        <AsyncLogger name="org.mongodb.driver.cluster" level="error" additivity="false">-->
        <!--            <appender-ref ref="RollingRandomAccessFile" />-->
        <!--            <appender-ref ref="ERROR" />-->
        <!--            <appender-ref ref="Console" />-->
        <!--        </AsyncLogger>-->
        <!--        <AsyncRoot level="${logLevel}">-->
        <!--            <appender-ref ref="RollingRandomAccessFile" />-->
        <!--            <appender-ref ref="ERROR" />-->
        <!--            <appender-ref ref="Console" />-->
        <!--        </AsyncRoot>-->

        <Root level="${logLevel}">
            <appender-ref ref="RollingRandomAccessFile"/>
            <appender-ref ref="ERROR"/>
            <appender-ref ref="Console"/>
        </Root>
    </loggers>
</configuration>



```

控制台打印：

![image-20221122204925532](F:\学习笔记\Notes\attachment\SpringBoot之日志集成.assets\image-20221122204925532.png)

#### 注意

1. 我们整个项目的日志框架应该统一，不允许多个模块出现不同的日志框架
2. 如果采用非默认的LogBack框架，必须先删除之前的默认框架相关信息
3. 日志输出格式以及我们开发中日志的输出尽量规范，不可以过多的打印无效日志又或者不打日志
4. 日志输出级别总共分为8种，不同的环境应该选择合适的日志级别进行输出，框架只会输出比选择级别高的日志。

日志初始化在ApplicationContext创建之前，所以@PropertySources加载的配置是读取不到的，系统环境变量、Spring Environment及application,bootstrap配置文件中的信息可以读取到。

读取系统环境属性：

```
<property name="LOG_PATH" value="${LOG_PATH:-E:/logs}" />
```

读取当前应用Environment中的属性：

```
<springProperty scope="context" name="fluentHost" source="myapp.fluentd.host"        defaultValue="localhost"/>
```

Spring Boot也支持通过springProfile来加载不同profiles下的配置。

```
<springProfile name="staging">    <!-- configuration to be enabled when the "staging" profile is active --></springProfile><springProfile name="dev, staging">    <!-- configuration to be enabled when the "dev" or "staging" profiles are active --></springProfile><springProfile name="!production">    <!-- configuration to be enabled when the "production" profile is not active --></springProfile>
```
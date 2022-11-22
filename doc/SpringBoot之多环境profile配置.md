### SpringBoot之多环境profile配置

#### Profile 

Profile 本质上代表一种用于组织配置信息的维度，在不同场景下可以代表不同的含义本质上代表一种用于组织配置信息的维度，在不同场景下可以代表不同的含义。

简单来说，Profile就是Spring Boot可以对不同环境或者指令来读取不同的配置文件。

为了达到集中化管理的目的，Spring Boot 对配置文件的命名也做了一定的约定，分别使用 label 和 profile 概念来指定配置信息的版本以及运行环境，其中 label 表示配置版本控制信息，而 profile 则用来指定该配置文件所对应的环境

在 Spring Boot 中，配置文件同时支持 .properties 和 .yml 两种文件格式，结合 label 和 profile 概念，如下所示的配置文件命名都是常见和合法的：

```
/{application}.yml

/{application}-{profile}.yml

/{label}/{application}-{profile}.yml

/{application}-{profile}.properties

/{label}/{application}-{profile}.properties


```

#### 配置使用

假如有开发、测试、生产三个不同的环境，需要定义三个不同环境下的配置。

1. 基于properties文件配置

    需要配置以下四个文件：

    1. applcation.properties
    2. application-dev.properties
    3. application-test.properties
    4. application-prod.properties

    然后在applcation.properties文件中指定当前的环境spring.profiles.active=test,这时候读取的就是application-test.properties文件。

2. 基于yml文件配置

    只需要一个applcation.yml文件就能搞定，推荐此方式。

    ```
    ---
    server:
      port: 8081
      
    spring:
      profiles: dev
    ---
    server:
      port: 8082
      
    spring:
      profiles: test
    ---
    server:
      port: 8083
      
    spring:
      profiles: prod
    ---
    #不同profile的激活,通过修改active的值，从而达到对不同的profile的激活
    spring:
      profiles:
        active: dev
    
    ```

    在一个yml文件里配置使用==---==对不通的文件进行区分，现在读取的是dev的配置文件

    

    #### 基于Java代码

    在JAVA配置代码中也可以加不同Profile下定义不同的配置文件，@Profile注解只能组合使用@Configuration和@Component注解。

    ```java
    @Configuration
    @Profile("prod")
    public classProductionConfiguration {   
    // ...
    }
    ```

    ## 指定Profile

    #### main方法启动方式：

    ```
    // 在Eclipse Arguments里面添加--spring.profiles.active=prod
    ```

    #### 插件启动方式：

    ```
    spring-boot:run -Drun.profiles=prod
    ```

    #### jar运行方式：

    ```
    java -jar xx.jar --spring.profiles.active=prod
    ```

    除了在配置文件和命令行中指定Profile，还可以在启动类中写死指定，通过SpringApplication.setAdditionalProfiles方法。

    SpringApplication.class

    ```
    public void setAdditionalProfiles(String... profiles) {    this.additionalProfiles = new LinkedHashSet<String>(Arrays.asList(profiles));}
    ```


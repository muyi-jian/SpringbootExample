### SpringBoot之Redis

- 创建项目

- pom配置

    ```xml
    		<dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-redis</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-pool2</artifactId>
            </dependency>
    ```

    完整pom

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.6.11</version>
            <relativePath/> <!-- lookup parent from repository -->
        </parent>
        <groupId>spring.boot</groupId>
        <artifactId>spring-boot-redis</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <name>spring-boot-redis</name>
        <description>Demo project for Spring Boot</description>
        <properties>
            <java.version>8</java.version>
        </properties>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-redis</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-pool2</artifactId>
            </dependency>
    
    
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
        </dependencies>
    
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <configuration>
                        <excludes>
                            <exclude>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                            </exclude>
                        </excludes>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    
    </project>
    
    ```

    

- prperties文件

    ```properties
    #Redis服务器地址
    spring.redis.host=192.168.56.20
    #Redis服务器连接端口
    spring.redis.port=6379
    # redis 密码
    spring.redis.password=123456
    #Redis默认情况下有16个分片，这里配置具体使用的分片，Redis数据库索引（默认为0）
    spring.redis.database= 0
    #连接超时时间（毫秒）
    spring.redis.timeout=1800000
    #连接池最大连接数（使用负值表示没有限制）
    spring.redis.lettuce.pool.max-active=20
    #最大阻塞等待时间(负数表示没限制)
    spring.redis.lettuce.pool.max-wait=-1
    #连接池中的最大空闲连接
    spring.redis.lettuce.pool.max-idle=5
    #连接池中的最小空闲连接
    spring.redis.lettuce.pool.min-idle=0
    ```

    

- redis配置类

    ```java
    package spring.boot.redis.config;
    
    import com.fasterxml.jackson.annotation.JsonAutoDetect;
    import com.fasterxml.jackson.annotation.PropertyAccessor;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
    import org.springframework.cache.CacheManager;
    import org.springframework.cache.annotation.CachingConfigurerSupport;
    import org.springframework.cache.annotation.EnableCaching;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.redis.cache.RedisCacheConfiguration;
    import org.springframework.data.redis.cache.RedisCacheManager;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.serializer.*;
    
    import java.time.Duration;
    
    @EnableCaching
    @Configuration
    public class RedisConfig extends CachingConfigurerSupport {
        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
            //使用
            /*RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
            template.setConnectionFactory(factory);
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            // 过期 om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                    ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            jackson2JsonRedisSerializer.setObjectMapper(om);
            //RedisSerializer<String> redisSerializer = new StringRedisSerializer();
            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
            // key采用String的序列化方式
            template.setKeySerializer(stringRedisSerializer);
            // hash的key也采用String的序列化方式
            template.setHashKeySerializer(stringRedisSerializer);
            // value序列化方式采用jackson
            template.setValueSerializer(jackson2JsonRedisSerializer);
            // hash的value序列化方式采用jackson
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            // 初始化 RedisTemplate 序列化完成
            template.afterPropertiesSet();
            return template;*/
            RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
            template.setConnectionFactory(factory);
            RedisSerializer jackson2JsonRedisSerializer = getJacksonSerializer();
            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
            // key采用String的序列化方式
            template.setKeySerializer(stringRedisSerializer);
            // hash的key也采用String的序列化方式
            template.setHashKeySerializer(stringRedisSerializer);
            // value序列化方式采用jackson
            template.setValueSerializer(jackson2JsonRedisSerializer);
            // hash的value序列化方式采用jackson
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            // 初始化 RedisTemplate 序列化完成
            template.afterPropertiesSet();
            return template;
        }
    
        private RedisSerializer getJacksonSerializer() {
    
    
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            // 过期 om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                    ObjectMapper.DefaultTyping.NON_FINAL);
            return new GenericJackson2JsonRedisSerializer(om);
        }
    
        @Bean
        public CacheManager cacheManager(RedisConnectionFactory factory) {
            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            //解决查询缓存转换异常的问题
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);
            // 配置序列化（解决乱码的问题）,过期时间600秒
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofSeconds(600))
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                    .disableCachingNullValues();
            RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                    .cacheDefaults(config)
                    .build();
            return cacheManager;
        }
    }
    
    ```

    

- 测试类

    ```java
    package spring.boot.redis.controller;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    @RestController
    @RequestMapping("/redisTest")
    public class RedisTestController {
        @Autowired
        private RedisTemplate redisTemplate;
    
        @GetMapping
        public String testRedis() {
            //设置值到redis
            redisTemplate.opsForValue().set("name", "lucy");
            // 从redis获取值
            String name = (String) redisTemplate.opsForValue().get("name");
            return name;
        }
    }
    
    
    ```

    

- 整体目录结构

    ![image-20230209135226498](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230209135226498.png)

- 测试

​	![image-20230209135337754](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230209135337754.png)



- 使用jedis

    

- x

- x

- x

- x
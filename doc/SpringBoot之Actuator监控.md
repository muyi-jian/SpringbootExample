### SpringBoot之Actuator监控

引入依赖

yml配置

配置中关闭了安全认证的功能，如果需要开启这个功能的话还需引入`spring-boot-starter-security`依赖。除了使用Spring Security来开启监控路径安全认证外，还可以使用Shiro对监控路径进行权限控制。

监控的端口和应用一致，配置`context-path`为`/monitor`，这样可以避免和自己应用的路径映射地址重复。

`endpoints.shutdown.enabled: true`提供了使用post请求来关闭Spring Boot应用的功能。

访问：http://localhost:8080/actuator   默认只开启health

```
{
	"_links": {
		"self": {
			"href": "http://localhost:8080/actuator",
			"templated": false
		},
		"health-path": {
			"href": "http://localhost:8080/actuator/health/{*path}",
			"templated": true
		},
		"health": {
			"href": "http://localhost:8080/actuator/health",
			"templated": false
		}
	}
}
```

配置开放其他：

```yml
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
```

访问：

```
{
	"_links": {
		"self": {
			"href": "http://localhost:8080/actuator",
			"templated": false
		},
		"beans": {
			"href": "http://localhost:8080/actuator/beans",
			"templated": false
		},
		"caches-cache": {
			"href": "http://localhost:8080/actuator/caches/{cache}",
			"templated": true
		},
		"caches": {
			"href": "http://localhost:8080/actuator/caches",
			"templated": false
		},
		"health": {
			"href": "http://localhost:8080/actuator/health",
			"templated": false
		},
		"health-path": {
			"href": "http://localhost:8080/actuator/health/{*path}",
			"templated": true
		},
		"info": {
			"href": "http://localhost:8080/actuator/info",
			"templated": false
		},
		"conditions": {
			"href": "http://localhost:8080/actuator/conditions",
			"templated": false
		},
		"configprops-prefix": {
			"href": "http://localhost:8080/actuator/configprops/{prefix}",
			"templated": true
		},
		"configprops": {
			"href": "http://localhost:8080/actuator/configprops",
			"templated": false
		},
		"env": {
			"href": "http://localhost:8080/actuator/env",
			"templated": false
		},
		"env-toMatch": {
			"href": "http://localhost:8080/actuator/env/{toMatch}",
			"templated": true
		},
		"loggers-name": {
			"href": "http://localhost:8080/actuator/loggers/{name}",
			"templated": true
		},
		"loggers": {
			"href": "http://localhost:8080/actuator/loggers",
			"templated": false
		},
		"heapdump": {
			"href": "http://localhost:8080/actuator/heapdump",
			"templated": false
		},
		"threaddump": {
			"href": "http://localhost:8080/actuator/threaddump",
			"templated": false
		},
		"metrics": {
			"href": "http://localhost:8080/actuator/metrics",
			"templated": false
		},
		"metrics-requiredMetricName": {
			"href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
			"templated": true
		},
		"scheduledtasks": {
			"href": "http://localhost:8080/actuator/scheduledtasks",
			"templated": false
		},
		"mappings": {
			"href": "http://localhost:8080/actuator/mappings",
			"templated": false
		}
	}
}
```

Actuator接口列表

| HTTP 方法 | 路径            | 描述                                                         |
| :-------- | :-------------- | :----------------------------------------------------------- |
| GET       | /autoconfig     | 提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过 |
| GET       | /configprops    | 描述配置属性(包含默认值)如何注入Bean                         |
| GET       | /beans          | 描述应用程序上下文里全部的Bean，以及它们的关系               |
| GET       | /dump           | 获取线程活动的快照                                           |
| GET       | /env            | 获取全部环境属性                                             |
| GET       | /env/{name}     | 根据名称获取特定的环境属性值                                 |
| GET       | /health         | 报告应用程序的健康指标，这些值由HealthIndicator的实现类提供  |
| GET       | /info           | 获取应用程序的定制信息，这些信息由info打头的属性提供         |
| GET       | /mappings       | 描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系 |
| GET       | /metrics        | 报告各种应用程序度量信息，比如内存用量和HTTP请求计数         |
| GET       | /metrics/{name} | 报告指定名称的应用程序度量值                                 |
| POST      | /shutdown       | 关闭应用程序，要求endpoints.shutdown.enabled设置为true       |
| GET       | /trace          | 提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)                 |



接口使用示例

### autoconfig

显示所有自动装配类的报告，以及是什么原因导致自动装配成功或者不成功。在浏览器输入：http://localhost/monitor/autoconfig，输出如下（截取部分）：

```
{
    "positiveMatches": {
        "AuditAutoConfiguration#auditListener": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.audit.listener.AbstractAuditListener; SearchStrategy: all) did not find any beans"
        }],
        "AuditAutoConfiguration.AuditEventRepositoryConfiguration": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.audit.AuditEventRepository; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#autoConfigurationReportEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnBean (types: org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport; SearchStrategy: all) found bean 'autoConfigurationReport'; @ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint; SearchStrategy: current) did not find any beans"
        }],
        "EndpointAutoConfiguration#beansEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.BeansEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#configurationPropertiesReportEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.ConfigurationPropertiesReportEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#dumpEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.DumpEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#environmentEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.EnvironmentEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#healthEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.HealthEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#infoEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.InfoEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#loggersEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnBean (types: org.springframework.boot.logging.LoggingSystem; SearchStrategy: all) found bean 'springBootLoggingSystem'; @ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.LoggersEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#metricsEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.MetricsEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#shutdownEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.ShutdownEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration#traceEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.TraceEndpoint; SearchStrategy: all) did not find any beans"
        }],
        "EndpointAutoConfiguration.RequestMappingEndpointConfiguration": [{
            "condition": "OnClassCondition",
            "message": "@ConditionalOnClass found required class 'org.springframework.web.servlet.handler.AbstractHandlerMethodMapping'; @ConditionalOnMissingClass did not find unwanted class"
        }],
        "EndpointAutoConfiguration.RequestMappingEndpointConfiguration#requestMappingEndpoint": [{
            "condition": "OnBeanCondition",
            "message": "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.RequestMappingEndpoint; SearchStrategy: all) did not find any beans"
        }],
...
}
```

### beans

查看Spring 容器管理的Bean,访问http://localhost/monitor/beans，输出如下（截取部分）：

```
[{
    "context": "application:80",
    "parent": null,
    "beans": [{
        "bean": "demoApplication",
        "aliases": [],
        "scope": "singleton",
        "type": "com.springboot.demo.DemoApplication$$EnhancerBySpringCGLIB$$77e6b2b4",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.core.type.classreading.CachingMetadataReaderFactory",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration$$EnhancerBySpringCGLIB$$dc9af024",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.condition.BeanTypeRegistry",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.condition.BeanTypeRegistry",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "propertySourcesPlaceholderConfigurer",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.context.support.PropertySourcesPlaceholderConfigurer",
        "resource": "class path resource [org/springframework/boot/autoconfigure/context/PropertyPlaceholderAutoConfiguration.class]",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$$EnhancerBySpringCGLIB$$a4dba584",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "standardJacksonObjectMapperBuilderCustomizer",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$StandardJackson2ObjectMapperBuilderCustomizer",
        "resource": "class path resource [org/springframework/boot/autoconfigure/jackson/JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration.class]",
        "dependencies": ["org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@78f30883", "spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties"]
    }, {
        "bean": "spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonProperties",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor",
        "resource": "null",
        "dependencies": ["org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.store"]
    }, {
        "bean": "org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.store",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.context.properties.ConfigurationBeanFactoryMetaData",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration$$EnhancerBySpringCGLIB$$e9c4bf95",
        "resource": "null",
        "dependencies": ["org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@78f30883"]
    }, {
        "bean": "jacksonObjectMapperBuilder",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.http.converter.json.Jackson2ObjectMapperBuilder",
        "resource": "class path resource [org/springframework/boot/autoconfigure/jackson/JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration.class]",
        "dependencies": ["standardJacksonObjectMapperBuilderCustomizer"]
    }, {
        "bean": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration$$EnhancerBySpringCGLIB$$3777fd7c",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "jacksonObjectMapper",
        "aliases": [],
        "scope": "singleton",
        "type": "com.fasterxml.jackson.databind.ObjectMapper",
        "resource": "class path resource [org/springframework/boot/autoconfigure/jackson/JacksonAutoConfiguration$JacksonObjectMapperConfiguration.class]",
        "dependencies": ["jacksonObjectMapperBuilder"]
    }, {
        "bean": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$$EnhancerBySpringCGLIB$$19839b0b",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "jsonComponentModule",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.jackson.JsonComponentModule",
        "resource": "class path resource [org/springframework/boot/autoconfigure/jackson/JacksonAutoConfiguration.class]",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration$TomcatWebSocketConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration$TomcatWebSocketConfiguration$$EnhancerBySpringCGLIB$$ab6fe14",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "websocketContainerCustomizer",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.websocket.TomcatWebSocketContainerCustomizer",
        "resource": "class path resource [org/springframework/boot/autoconfigure/websocket/WebSocketAutoConfiguration$TomcatWebSocketConfiguration.class]",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration$$EnhancerBySpringCGLIB$$ee951243",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration$EmbeddedTomcat",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration$EmbeddedTomcat$$EnhancerBySpringCGLIB$$8f67c107",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "tomcatEmbeddedServletContainerFactory",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory",
        "resource": "class path resource [org/springframework/boot/autoconfigure/web/EmbeddedServletContainerAutoConfiguration$EmbeddedTomcat.class]",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration$$EnhancerBySpringCGLIB$$48514e2b",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "embeddedServletContainerCustomizerBeanPostProcessor",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "errorPageRegistrarBeanPostProcessor",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.web.servlet.ErrorPageRegistrarBeanPostProcessor",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletConfiguration$$EnhancerBySpringCGLIB$$824a2d3d",
        "resource": "null",
        "dependencies": ["spring.mvc-org.springframework.boot.autoconfigure.web.WebMvcProperties"]
    }, {
        "bean": "dispatcherServlet",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.web.servlet.DispatcherServlet",
        "resource": "class path resource [org/springframework/boot/autoconfigure/web/DispatcherServletAutoConfiguration$DispatcherServletConfiguration.class]",
        "dependencies": []
    }, {
        "bean": "spring.mvc-org.springframework.boot.autoconfigure.web.WebMvcProperties",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.WebMvcProperties",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration$$EnhancerBySpringCGLIB$$fd39bc64",
        "resource": "null",
        "dependencies": ["serverProperties", "spring.mvc-org.springframework.boot.autoconfigure.web.WebMvcProperties"]
    }, {
        "bean": "dispatcherServletRegistration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.web.servlet.ServletRegistrationBean",
        "resource": "class path resource [org/springframework/boot/autoconfigure/web/DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration.class]",
        "dependencies": ["dispatcherServlet"]
    }, {
        "bean": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$$EnhancerBySpringCGLIB$$d158c03f",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration$$EnhancerBySpringCGLIB$$4ce40e75",
        "resource": "null",
        "dependencies": []
    }, {
        "bean": "methodValidationPostProcessor",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.validation.beanvalidation.MethodValidationPostProcessor",
        "resource": "class path resource [org/springframework/boot/autoconfigure/validation/ValidationAutoConfiguration.class]",
        "dependencies": ["environment"]
    },
    ......
     {
        "bean": "org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration",
        "aliases": [],
        "scope": "singleton",
        "type": "org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration$$EnhancerBySpringCGLIB$$ec83a29e",
        "resource": "null",
        "dependencies": []
    }]
}]
```

### configprops

所有`＠ConfigurationProperties`注解的配置信息，如文件上传的最大允许配置等。访问http://localhost/monitor/configprops，输出如下：

```
{
    "environmentMvcEndpoint": {
        "prefix": "endpoints.env",
        "properties": {
            "path": "/env"
        }
    },
    "heapdumpMvcEndpoint": {
        "prefix": "endpoints.heapdump",
        "properties": {
            "enabled": true,
            "path": "/heapdump",
            "sensitive": true
        }
    },
    "managementServerProperties": {
        "prefix": "management",
        "properties": {
            "port": 80,
            "addApplicationContextHeader": true,
            "address": null,
            "contextPath": "/monitor",
            "ssl": null,
            "security": {
                "enabled": false,
                "roles": ["ACTUATOR"],
                "sessions": "STATELESS"
            }
        }
    },
    "spring.mvc-org.springframework.boot.autoconfigure.web.WebMvcProperties": {
        "prefix": "spring.mvc",
        "properties": {
            "locale": null,
            "mediaTypes": {},
            "localeResolver": "ACCEPT_HEADER",
            "messageCodesResolverFormat": null,
            "async": {
                "requestTimeout": null
            },
            "dispatchOptionsRequest": true,
            "servlet": {
                "loadOnStartup": -1
            },
            "ignoreDefaultModelOnRedirect": true,
            "dispatchTraceRequest": false,
            "staticPathPattern": "/**",
            "dateFormat": null,
            "view": {
                "prefix": null,
                "suffix": null
            },
            "logResolvedException": false,
            "throwExceptionIfNoHandlerFound": false
        }
    },
    "spring.http.multipart-org.springframework.boot.autoconfigure.web.MultipartProperties": {
        "prefix": "spring.http.multipart",
        "properties": {
            "enabled": true,
            "maxRequestSize": "10MB",
            "location": null,
            "resolveLazily": false,
            "fileSizeThreshold": "0",
            "maxFileSize": "1MB"
        }
    },
    "spring.http.encoding-org.springframework.boot.autoconfigure.web.HttpEncodingProperties": {
        "prefix": "spring.http.encoding",
        "properties": {
            "charset": "UTF-8",
            "mapping": null,
            "force": false,
            "forceResponse": false,
            "forceRequest": false
        }
    },
    "endpoints.health-org.springframework.boot.actuate.autoconfigure.HealthMvcEndpointProperties": {
        "prefix": "endpoints.health",
        "properties": {
            "mapping": {}
        }
    },
    "spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties": {
        "prefix": "spring.resources",
        "properties": {
            "staticLocations": ["/", "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"],
            "addMappings": true,
            "chain": {
                "cache": true,
                "htmlApplicationCache": false,
                "gzipped": false,
                "strategy": {
                    "fixed": {
                        "enabled": false,
                        "paths": ["/**"],
                        "version": null
                    },
                    "content": {
                        "enabled": false,
                        "paths": ["/**"]
                    }
                }
            },
            "cachePeriod": null
        }
    },
    "shutdownMvcEndpoint": {
        "prefix": "endpoints.shutdown",
        "properties": {
            "path": "/shutdown"
        }
    },
    "requestMappingEndpoint": {
        "prefix": "endpoints.mappings",
        "properties": {
            "id": "mappings",
            "enabled": true,
            "sensitive": true
        }
    },
    "autoConfigurationReportEndpoint": {
        "prefix": "endpoints.autoconfig",
        "properties": {
            "id": "autoconfig",
            "enabled": true,
            "sensitive": true
        }
    },
    "management.trace-org.springframework.boot.actuate.trace.TraceProperties": {
        "prefix": "management.trace",
        "properties": {
            "include": ["TIME_TAKEN", "ERRORS", "REQUEST_HEADERS", "COOKIES", "RESPONSE_HEADERS"]
        }
    },
    "management.info-org.springframework.boot.actuate.autoconfigure.InfoContributorProperties": {
        "prefix": "management.info",
        "properties": {
            "git": {
                "mode": "SIMPLE"
            }
        }
    },
    "loggersEndpoint": {
        "prefix": "endpoints.loggers",
        "properties": {
            "id": "loggers",
            "enabled": true,
            "sensitive": true
        }
    },
    "spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties": {
        "prefix": "spring.jackson",
        "properties": {
            "deserialization": {},
            "defaultPropertyInclusion": null,
            "propertyNamingStrategy": null,
            "locale": null,
            "serialization": {},
            "jodaDateTimeFormat": null,
            "mapper": {},
            "generator": {},
            "timeZone": null,
            "dateFormat": null,
            "parser": {}
        }
    },
    "shutdownEndpoint": {
        "prefix": "endpoints.shutdown",
        "properties": {
            "id": "shutdown",
            "enabled": true,
            "sensitive": true
        }
    },
    "endpoints.metrics.filter-org.springframework.boot.actuate.autoconfigure.MetricFilterProperties": {
        "prefix": "endpoints.metrics.filter",
        "properties": {
            "counterSubmissions": ["MERGED"],
            "gaugeSubmissions": ["MERGED"]
        }
    },
    "loggersMvcEndpoint": {
        "prefix": "endpoints.loggers",
        "properties": {
            "path": "/loggers"
        }
    },
    "dumpEndpoint": {
        "prefix": "endpoints.dump",
        "properties": {
            "id": "dump",
            "enabled": true,
            "sensitive": true
        }
    },
    "endpoints-org.springframework.boot.actuate.endpoint.EndpointProperties": {
        "prefix": "endpoints",
        "properties": {
            "enabled": true,
            "sensitive": null
        }
    },
    "metricsMvcEndpoint": {
        "prefix": "endpoints.metrics",
        "properties": {
            "path": "/metrics"
        }
    },
    "metricsEndpoint": {
        "prefix": "endpoints.metrics",
        "properties": {
            "id": "metrics",
            "enabled": true,
            "sensitive": true
        }
    },
    "healthEndpoint": {
        "prefix": "endpoints.health",
        "properties": {
            "id": "health",
            "enabled": true,
            "timeToLive": 1000,
            "sensitive": false
        }
    },
    "auditEventMvcEndpoint": {
        "prefix": "endpoints.auditevents",
        "properties": {
            "enabled": true,
            "path": "/auditevents",
            "sensitive": true
        }
    },
    "healthMvcEndpoint": {
        "prefix": "endpoints.health",
        "properties": {
            "path": "/health"
        }
    },
    "endpoints.cors-org.springframework.boot.actuate.autoconfigure.EndpointCorsProperties": {
        "prefix": "endpoints.cors",
        "properties": {
            "maxAge": 1800,
            "exposedHeaders": [],
            "allowedHeaders": [],
            "allowedOrigins": [],
            "allowedMethods": [],
            "allowCredentials": null
        }
    },
    "diskSpaceHealthIndicatorProperties": {
        "prefix": "management.health.diskspace",
        "properties": {
            "path": "C:\\Users\\Administrator\\Desktop\\01.Start-Spring-Boot\\.",
            "threshold": 10485760
        }
    },
    "beansEndpoint": {
        "prefix": "endpoints.beans",
        "properties": {
            "id": "beans",
            "enabled": true,
            "sensitive": true
        }
    },
    "traceEndpoint": {
        "prefix": "endpoints.trace",
        "properties": {
            "id": "trace",
            "enabled": true,
            "sensitive": true
        }
    },
    "auditEventsEndpoint": {
        "prefix": "endpoints.auditevents",
        "properties": {
            "enabled": true
        }
    },
    "endpoints.jmx-org.springframework.boot.actuate.autoconfigure.EndpointMBeanExportProperties": {
        "prefix": "endpoints.jmx",
        "properties": {
            "enabled": true,
            "uniqueNames": false,
            "domain": "",
            "staticNames": {}
        }
    },
    "spring.metrics.export-org.springframework.boot.actuate.metrics.export.MetricExportProperties": {
        "prefix": "spring.metrics.export",
        "properties": {
            "enabled": true,
            "redis": {
                "prefix": "spring.metrics.application.7f8f2ef33a0f173c075f96c8f5c5b4fd",
                "key": "******"
            },
            "aggregate": {
                "prefix": "application.7f8f2ef33a0f173c075f96c8f5c5b4fd",
                "keyPattern": "k.d"
            },
            "excludes": null,
            "statsd": {
                "host": null,
                "port": 8125,
                "prefix": null
            },
            "triggers": {},
            "includes": null
        }
    },
    "configurationPropertiesReportEndpoint": {
        "prefix": "endpoints.configprops",
        "properties": {
            "id": "configprops",
            "enabled": true,
            "sensitive": true
        }
    },
    "spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties": {
        "prefix": "spring.info",
        "properties": {
            "git": {
                "location": {}
            },
            "build": {
                "location": {}
            }
        }
    },
    "infoEndpoint": {
        "prefix": "endpoints.info",
        "properties": {
            "id": "info",
            "enabled": true,
            "sensitive": false
        }
    },
    "serverProperties": {
        "prefix": "server",
        "properties": {
            "undertow": {
                "maxHttpPostSize": 0,
                "bufferSize": null,
                "buffersPerRegion": null,
                "ioThreads": null,
                "workerThreads": null,
                "directBuffers": null,
                "accesslog": {
                    "enabled": null,
                    "pattern": "common",
                    "prefix": "access_log.",
                    "suffix": "log",
                    "dir": "C:\\Users\\Administrator\\Desktop\\01.Start-Spring-Boot\\logs",
                    "rotate": true
                }
            },
            "port": 80,
            "error": {
                "path": "/error",
                "includeStacktrace": "NEVER"
            },
            "maxHttpHeaderSize": 0,
            "jspServlet": null,
            "ssl": null,
            "maxHttpPostSize": 0,
            "jetty": {
                "maxHttpPostSize": 0,
                "acceptors": null,
                "selectors": null
            },
            "servletPath": "/",
            "tomcat": {
                "accesslog": {
                    "enabled": false,
                    "pattern": "common",
                    "directory": "logs",
                    "prefix": "access_log",
                    "suffix": ".log",
                    "rotate": true,
                    "renameOnRotate": false,
                    "fileDateFormat": ".yyyy-MM-dd",
                    "requestAttributesEnabled": false,
                    "buffered": true
                },
                "internalProxies": "10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|192\\.168\\.\\d{1,3}\\.\\d{1,3}|169\\.254\\.\\d{1,3}\\.\\d{1,3}|127\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|172\\.1[6-9]{1}\\.\\d{1,3}\\.\\d{1,3}|172\\.2[0-9]{1}\\.\\d{1,3}\\.\\d{1,3}|172\\.3[0-1]{1}\\.\\d{1,3}\\.\\d{1,3}",
                "protocolHeader": null,
                "protocolHeaderHttpsValue": "https",
                "portHeader": "X-Forwarded-Port",
                "remoteIpHeader": null,
                "basedir": null,
                "backgroundProcessorDelay": 30,
                "maxThreads": 0,
                "minSpareThreads": 0,
                "maxHttpPostSize": 0,
                "redirectContextRoot": null,
                "uriEncoding": null,
                "maxConnections": 0,
                "acceptCount": 0,
                "additionalTldSkipPatterns": []
            },
            "connectionTimeout": null,
            "session": {
                "timeout": null,
                "trackingModes": null,
                "persistent": false,
                "storeDir": null,
                "cookie": {
                    "name": null,
                    "domain": null,
                    "path": null,
                    "comment": null,
                    "httpOnly": null,
                    "secure": null,
                    "maxAge": null
                }
            },
            "address": null,
            "contextParameters": {},
            "serverHeader": null,
            "useForwardHeaders": null,
            "contextPath": null,
            "displayName": "application"
        }
    },
    "management.health.status-org.springframework.boot.actuate.autoconfigure.HealthIndicatorProperties": {
        "prefix": "management.health.status",
        "properties": {
            "order": null
        }
    },
    "environmentEndpoint": {
        "prefix": "endpoints.env",
        "properties": {
            "id": "env",
            "enabled": true,
            "sensitive": true
        }
    }
}
```

### trace

`/trace`接口能查看最近的HTTP 请求和响应，在浏览器输入：http://localhost/monitor/trace，输出如下：

```
[{
    "timestamp": 1525657638444,
    "info": {
        "method": "GET",
        "path": "/login;JSESSIONID=c1d0a83c-05fd-479c-ae6b-2ecc9b878a4a",
        "headers": {
            "request": {
                "host": "localhost",
                "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0",
                "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                "accept-language": "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
                "accept-encoding": "gzip, deflate",
                "connection": "keep-alive",
                "upgrade-insecure-requests": "1",
                "pragma": "no-cache",
                "cache-control": "no-cache"
            },
            "response": {
                "X-Application-Context": "application:80",
                "status": "404"
            }
        },
        "timeTaken": "11"
    }
}, {
    "timestamp": 1525657495017,
    "info": {
        "method": "GET",
        "path": "/monitor/beans",
        "headers": {
            "request": {
                "host": "localhost",
                "connection": "keep-alive",
                "upgrade-insecure-requests": "1",
                "user-agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.10 Safari/537.36",
                "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                "accept-encoding": "gzip, deflate, br",
                "accept-language": "zh-CN,zh;q=0.9,en;q=0.8"
            },
            "response": {
                "X-Application-Context": "application:80",
                "Content-Type": "application/vnd.spring-boot.actuator.v1+json;charset=UTF-8",
                "Transfer-Encoding": "chunked",
                "Date": "Mon, 07 May 2018 01:44:55 GMT",
                "status": "200"
            }
		},
        "timeTaken": "609"
    }
}, {
    "timestamp": 1525657402454,
    "info": {
        "method": "GET",
        "path": "/favicon.ico",
        "headers": {
            "request": {
                "host": "localhost",
                "connection": "keep-alive",
                "pragma": "no-cache",
                "cache-control": "no-cache",
                "user-agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.10 Safari/537.36",
                "accept": "image/webp,image/apng,image/*,*/*;q=0.8",
                "referer": "http://localhost/monitor/autoconfig",
                "accept-encoding": "gzip, deflate, br",
                "accept-language": "zh-CN,zh;q=0.9,en;q=0.8"
            },
            "response": {
                "X-Application-Context": "application:80",
                "Last-Modified": "Wed, 29 Nov 2017 01:54:48 GMT",
                "Accept-Ranges": "bytes",
                "Content-Type": "application/octet-stream",
                "Content-Length": "946",
                "Date": "Mon, 07 May 2018 01:43:22 GMT",
                "status": "200"
            }
        },
        "timeTaken": "21"
    }
}, {
    "timestamp": 1525657402076,
    "info": {
        "method": "GET",
        "path": "/monitor/autoconfig",
        "headers": {
            "request": {
                "host": "localhost",
                "connection": "keep-alive",
                "cache-control": "max-age=0",
                "upgrade-insecure-requests": "1",
                "user-agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.10 Safari/537.36",
                "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                "accept-encoding": "gzip, deflate, br",
                "accept-language": "zh-CN,zh;q=0.9,en;q=0.8"
            },
            "response": {
                "X-Application-Context": "application:80",
                "Content-Type": "application/vnd.spring-boot.actuator.v1+json;charset=UTF-8",
                "Transfer-Encoding": "chunked",
                "Date": "Mon, 07 May 2018 01:43:22 GMT",
                "status": "200"
            }
        },
        "timeTaken": "300"
    }
}]
```



### dump

获取某一时刻虚拟机线程栈信息。线程栈表示某一时刻虚拟机正在做的事情，访问http://localhost/monitor/dump，输出如下（截取部分）：

```
[{
    "threadName": "DestroyJavaVM",
    "threadId": 32,
    "blockedTime": -1,
    "blockedCount": 0,
    "waitedTime": -1,
    "waitedCount": 0,
    "lockName": null,
    "lockOwnerId": -1,
    "lockOwnerName": null,
    "inNative": false,
    "suspended": false,
    "threadState": "RUNNABLE",
    "stackTrace": [],
    "lockedMonitors": [],
    "lockedSynchronizers": [],
    "lockInfo": null
}, {
    "threadName": "http-nio-80-AsyncTimeout",
    "threadId": 30,
    "blockedTime": -1,
    "blockedCount": 0,
    "waitedTime": -1,
    "waitedCount": 14903,
    "lockName": null,
    "lockOwnerId": -1,
    "lockOwnerName": null,
    "inNative": false,
    "suspended": false,
    "threadState": "TIMED_WAITING",
    "stackTrace": [{
        "methodName": "sleep",
        "fileName": "Thread.java",
        "lineNumber": -2,
        "className": "java.lang.Thread",
        "nativeMethod": true
    }, {
        "methodName": "run",
        "fileName": "AbstractProtocol.java",
        "lineNumber": 1211,
        "className": "org.apache.coyote.AbstractProtocol$AsyncTimeout",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "Thread.java",
        "lineNumber": 745,
        "className": "java.lang.Thread",
        "nativeMethod": false
    }],
    "lockedMonitors": [],
    "lockedSynchronizers": [],
    "lockInfo": null
}, {
    "threadName": "http-nio-80-Acceptor-0",
    "threadId": 29,
    "blockedTime": -1,
    "blockedCount": 0,
    "waitedTime": -1,
    "waitedCount": 0,
    "lockName": null,
    "lockOwnerId": -1,
    "lockOwnerName": null,
    "inNative": true,
    "suspended": false,
    "threadState": "RUNNABLE",
    "stackTrace": [{
        "methodName": "accept0",
        "fileName": "ServerSocketChannelImpl.java",
        "lineNumber": -2,
        "className": "sun.nio.ch.ServerSocketChannelImpl",
        "nativeMethod": true
    }, {
        "methodName": "accept",
        "fileName": "ServerSocketChannelImpl.java",
        "lineNumber": 250,
        "className": "sun.nio.ch.ServerSocketChannelImpl",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "NioEndpoint.java",
        "lineNumber": 455,
        "className": "org.apache.tomcat.util.net.NioEndpoint$Acceptor",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "Thread.java",
        "lineNumber": 745,
        "className": "java.lang.Thread",
        "nativeMethod": false
    }],
    "lockedMonitors": [{
        "className": "java.lang.Object",
        "identityHashCode": 1985974129,
        "lockedStackDepth": 1,
        "lockedStackFrame": {
            "methodName": "accept",
            "fileName": "ServerSocketChannelImpl.java",
            "lineNumber": 250,
            "className": "sun.nio.ch.ServerSocketChannelImpl",
            "nativeMethod": false
        }
    }],
    "lockedSynchronizers": [],
    "lockInfo": null
}, {
    "threadName": "http-nio-80-ClientPoller-1",
    "threadId": 28,
    "blockedTime": -1,
    "blockedCount": 6,
    "waitedTime": -1,
    "waitedCount": 0,
    "lockName": null,
    "lockOwnerId": -1,
    "lockOwnerName": null,
    "inNative": true,
    "suspended": false,
    "threadState": "RUNNABLE",
    "stackTrace": [{
        "methodName": "poll0",
        "fileName": "WindowsSelectorImpl.java",
        "lineNumber": -2,
        "className": "sun.nio.ch.WindowsSelectorImpl$SubSelector",
        "nativeMethod": true
    }, {
        "methodName": "poll",
        "fileName": "WindowsSelectorImpl.java",
        "lineNumber": 296,
        "className": "sun.nio.ch.WindowsSelectorImpl$SubSelector",
        "nativeMethod": false
    }, {
        "methodName": "access$400",
        "fileName": "WindowsSelectorImpl.java",
        "lineNumber": 278,
        "className": "sun.nio.ch.WindowsSelectorImpl$SubSelector",
        "nativeMethod": false
    }, {
        "methodName": "doSelect",
        "fileName": "WindowsSelectorImpl.java",
        "lineNumber": 159,
        "className": "sun.nio.ch.WindowsSelectorImpl",
        "nativeMethod": false
    }, {
        "methodName": "lockAndDoSelect",
        "fileName": "SelectorImpl.java",
        "lineNumber": 87,
        "className": "sun.nio.ch.SelectorImpl",
        "nativeMethod": false
    }, {
        "methodName": "select",
        "fileName": "SelectorImpl.java",
        "lineNumber": 98,
        "className": "sun.nio.ch.SelectorImpl",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "NioEndpoint.java",
        "lineNumber": 793,
        "className": "org.apache.tomcat.util.net.NioEndpoint$Poller",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "Thread.java",
        "lineNumber": 745,
        "className": "java.lang.Thread",
        "nativeMethod": false
    }],
    "lockedMonitors": [{
        "className": "sun.nio.ch.Util$2",
        "identityHashCode": 511554453,
        "lockedStackDepth": 4,
        "lockedStackFrame": {
            "methodName": "lockAndDoSelect",
            "fileName": "SelectorImpl.java",
            "lineNumber": 87,
            "className": "sun.nio.ch.SelectorImpl",
            "nativeMethod": false
        }
    }, {
        "className": "java.util.Collections$UnmodifiableSet",
        "identityHashCode": 563515370,
        "lockedStackDepth": 4,
        "lockedStackFrame": {
            "methodName": "lockAndDoSelect",
            "fileName": "SelectorImpl.java",
            "lineNumber": 87,
            "className": "sun.nio.ch.SelectorImpl",
            "nativeMethod": false
        }
    }, {
        "className": "sun.nio.ch.WindowsSelectorImpl",
        "identityHashCode": 761235575,
        "lockedStackDepth": 4,
        "lockedStackFrame": {
            "methodName": "lockAndDoSelect",
            "fileName": "SelectorImpl.java",
            "lineNumber": 87,
            "className": "sun.nio.ch.SelectorImpl",
            "nativeMethod": false
        }
    }],
    "lockedSynchronizers": [],
    "lockInfo": null
},
......
 {
    "threadName": "Finalizer",
    "threadId": 3,
    "blockedTime": -1,
    "blockedCount": 202,
    "waitedTime": -1,
    "waitedCount": 51,
    "lockName": "java.lang.ref.ReferenceQueue$Lock@bd5f3f3",
    "lockOwnerId": -1,
    "lockOwnerName": null,
    "inNative": false,
    "suspended": false,
    "threadState": "WAITING",
    "stackTrace": [{
        "methodName": "wait",
        "fileName": "Object.java",
        "lineNumber": -2,
        "className": "java.lang.Object",
        "nativeMethod": true
    }, {
        "methodName": "remove",
        "fileName": "ReferenceQueue.java",
        "lineNumber": 135,
        "className": "java.lang.ref.ReferenceQueue",
        "nativeMethod": false
    }, {
        "methodName": "remove",
        "fileName": "ReferenceQueue.java",
        "lineNumber": 151,
        "className": "java.lang.ref.ReferenceQueue",
        "nativeMethod": false
    }, {
        "methodName": "run",
        "fileName": "Finalizer.java",
        "lineNumber": 209,
        "className": "java.lang.ref.Finalizer$FinalizerThread",
        "nativeMethod": false
    }],
    "lockedMonitors": [],
    "lockedSynchronizers": [],
    "lockInfo": {
        "className": "java.lang.ref.ReferenceQueue$Lock",
        "identityHashCode": 198570995
    }
}]
```



### env

显示Spring Boot环境变量，如使用的JDK版本、加载的jar包、配置文件信息、日志文件信息。访问，输出如下：

```
{
    "profiles": [],
    "server.ports": {
        "local.server.port": 80
    },
    "servletContextInitParams": {},
    "systemProperties": {
        "java.runtime.name": "Java(TM) SE Runtime Environment",
        "sun.boot.library.path": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\bin",
        "java.vm.version": "24.80-b11",
        "java.vm.vendor": "Oracle Corporation",
        "java.vendor.url": "http://java.oracle.com/",
        "path.separator": ";",
        "java.vm.name": "Java HotSpot(TM) 64-Bit Server VM",
        "file.encoding.pkg": "sun.io",
        "user.country": "CN",
        "user.script": "",
        "sun.java.launcher": "SUN_STANDARD",
        "sun.os.patch.level": "",
        "PID": "15184",
        "java.vm.specification.name": "Java Virtual Machine Specification",
        "user.dir": "C:\\Users\\Administrator\\Desktop\\01.Start-Spring-Boot",
        "java.runtime.version": "1.7.0_80-b15",
        "java.awt.graphicsenv": "sun.awt.Win32GraphicsEnvironment",
        "org.jboss.logging.provider": "slf4j",
        "java.endorsed.dirs": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\endorsed",
        "os.arch": "amd64",
        "java.io.tmpdir": "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\",
        "line.separator": "\r\n",
        "java.vm.specification.vendor": "Oracle Corporation",
        "user.variant": "",
        "os.name": "Windows 8.1",
        "sun.jnu.encoding": "GBK",
        "spring.beaninfo.ignore": "true",
        "java.library.path": "C:\\Program Files\\Java\\jdk1.7.0_80\\bin;C:\\Windows\\Sun\\Java\\bin;C:\\Windows\\system32;C:\\Windows;C:/Program Files/Java/jre1.8.0_31/bin/server;C:/Program Files/Java/jre1.8.0_31/bin;C:/Program Files/Java/jre1.8.0_31/lib/amd64;f:\\app\\Administrator\\product\\11.2.0\\client_1\\bin;f:\\app\\Administrator\\product\\11.2.0\\dbhome_1\\bin;C:\\ProgramData\\Oracle\\Java\\javapath;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Program Files\\nodejs\\;C:\\Program Files\\Git\\cmd;\"%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin\";%M2_HOME%\\bin;C:\\Program Files\\TortoiseSVN\\bin;C:\\Program Files\\PuTTY\\;C:\\ProgramData\\chocolatey\\bin;C:\\Users\\Administrator\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Users\\Administrator\\AppData\\Roaming\\npm;\"C:\\Program Files\\Java\\jdk1.8.0_31\\bin;C:\\Program Files\\Java\\jdk1.8.0_31\\jre\\bin\";D:\\Program Files\\apache-maven-3.3.9-bin\\apache-maven-3.3.9\\bin;C:\\Program Files\\cmder;;D:\\Program Files\\Oxygen;;.",
        "java.specification.name": "Java Platform API Specification",
        "java.class.version": "51.0",
        "sun.management.compiler": "HotSpot 64-Bit Tiered Compilers",
        "os.version": "6.3",
        "user.home": "C:\\Users\\Administrator",
        "catalina.useNaming": "false",
        "user.timezone": "Asia/Shanghai",
        "java.awt.printerjob": "sun.awt.windows.WPrinterJob",
        "file.encoding": "UTF-8",
        "java.specification.version": "1.7",
        "catalina.home": "C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat.2042919348800278549.80",
        "java.class.path": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\rt.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\access-bridge-64.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\dnsns.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\jaccess.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\localedata.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\sunec.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\sunjce_provider.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\sunmscapi.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext\\zipfs.jar;C:\\Users\\Administrator\\Desktop\\01.Start-Spring-Boot\\target\\classes;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-starter-web\\1.5.9.RELEASE\\spring-boot-starter-web-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-starter\\1.5.9.RELEASE\\spring-boot-starter-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot\\1.5.9.RELEASE\\spring-boot-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-autoconfigure\\1.5.9.RELEASE\\spring-boot-autoconfigure-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-starter-logging\\1.5.9.RELEASE\\spring-boot-starter-logging-1.5.9.RELEASE.jar;D:\\m2\\repository\\ch\\qos\\logback\\logback-classic\\1.1.11\\logback-classic-1.1.11.jar;D:\\m2\\repository\\ch\\qos\\logback\\logback-core\\1.1.11\\logback-core-1.1.11.jar;D:\\m2\\repository\\org\\slf4j\\jcl-over-slf4j\\1.7.25\\jcl-over-slf4j-1.7.25.jar;D:\\m2\\repository\\org\\slf4j\\jul-to-slf4j\\1.7.25\\jul-to-slf4j-1.7.25.jar;D:\\m2\\repository\\org\\slf4j\\log4j-over-slf4j\\1.7.25\\log4j-over-slf4j-1.7.25.jar;D:\\m2\\repository\\org\\yaml\\snakeyaml\\1.17\\snakeyaml-1.17.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-starter-tomcat\\1.5.9.RELEASE\\spring-boot-starter-tomcat-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-core\\8.5.23\\tomcat-embed-core-8.5.23.jar;D:\\m2\\repository\\org\\apache\\tomcat\\tomcat-annotations-api\\8.5.23\\tomcat-annotations-api-8.5.23.jar;D:\\m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-el\\8.5.23\\tomcat-embed-el-8.5.23.jar;D:\\m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-websocket\\8.5.23\\tomcat-embed-websocket-8.5.23.jar;D:\\m2\\repository\\org\\hibernate\\hibernate-validator\\5.3.6.Final\\hibernate-validator-5.3.6.Final.jar;D:\\m2\\repository\\javax\\validation\\validation-api\\1.1.0.Final\\validation-api-1.1.0.Final.jar;D:\\m2\\repository\\org\\jboss\\logging\\jboss-logging\\3.3.1.Final\\jboss-logging-3.3.1.Final.jar;D:\\m2\\repository\\com\\fasterxml\\classmate\\1.3.4\\classmate-1.3.4.jar;D:\\m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.8.10\\jackson-databind-2.8.10.jar;D:\\m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.8.0\\jackson-annotations-2.8.0.jar;D:\\m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.8.10\\jackson-core-2.8.10.jar;D:\\m2\\repository\\org\\springframework\\spring-web\\4.3.13.RELEASE\\spring-web-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\spring-aop\\4.3.13.RELEASE\\spring-aop-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\spring-beans\\4.3.13.RELEASE\\spring-beans-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\spring-context\\4.3.13.RELEASE\\spring-context-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\spring-webmvc\\4.3.13.RELEASE\\spring-webmvc-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\spring-expression\\4.3.13.RELEASE\\spring-expression-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\slf4j\\slf4j-api\\1.7.25\\slf4j-api-1.7.25.jar;D:\\m2\\repository\\org\\springframework\\spring-core\\4.3.13.RELEASE\\spring-core-4.3.13.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-starter-actuator\\1.5.9.RELEASE\\spring-boot-starter-actuator-1.5.9.RELEASE.jar;D:\\m2\\repository\\org\\springframework\\boot\\spring-boot-actuator\\1.5.9.RELEASE\\spring-boot-actuator-1.5.9.RELEASE.jar",
        "user.name": "Administrator",
        "java.vm.specification.version": "1.7",
        "sun.java.command": "com.springboot.demo.DemoApplication",
        "java.home": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre",
        "sun.arch.data.model": "64",
        "user.language": "zh",
        "java.specification.vendor": "Oracle Corporation",
        "awt.toolkit": "sun.awt.windows.WToolkit",
        "java.vm.info": "mixed mode",
        "java.version": "1.7.0_80",
        "java.ext.dirs": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\ext;C:\\Windows\\Sun\\Java\\lib\\ext",
        "sun.boot.class.path": "C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\rt.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\sunrsasign.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.7.0_80\\jre\\classes",
        "java.awt.headless": "true",
        "java.vendor": "Oracle Corporation",
        "catalina.base": "C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat.2042919348800278549.80",
        "file.separator": "\\",
        "java.vendor.url.bug": "http://bugreport.sun.com/bugreport/",
        "sun.io.unicode.encoding": "UnicodeLittle",
        "sun.cpu.endian": "little",
        "sun.desktop": "windows",
        "sun.cpu.isalist": "amd64"
    },
    "systemEnvironment": {
        "USERPROFILE": "C:\\Users\\Administrator",
        "ProgramData": "C:\\ProgramData",
        "PATHEXT": ".COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC",
        "JAVA_HOME": "C:\\Program Files\\Java\\jdk1.8.0_31",
        "ProgramFiles(x86)": "C:\\Program Files (x86)",
        "ChocolateyLastPathUpdate": "周四 4月 26 09:03:47 2018",
        "TEMP": "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp",
        "SystemDrive": "C:",
        "ProgramFiles": "C:\\Program Files",
        "Path": "C:/Program Files/Java/jre1.8.0_31/bin/server;C:/Program Files/Java/jre1.8.0_31/bin;C:/Program Files/Java/jre1.8.0_31/lib/amd64;f:\\app\\Administrator\\product\\11.2.0\\client_1\\bin;f:\\app\\Administrator\\product\\11.2.0\\dbhome_1\\bin;C:\\ProgramData\\Oracle\\Java\\javapath;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Program Files\\nodejs\\;C:\\Program Files\\Git\\cmd;\"%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin\";%M2_HOME%\\bin;C:\\Program Files\\TortoiseSVN\\bin;C:\\Program Files\\PuTTY\\;C:\\ProgramData\\chocolatey\\bin;C:\\Users\\Administrator\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Users\\Administrator\\AppData\\Roaming\\npm;\"C:\\Program Files\\Java\\jdk1.8.0_31\\bin;C:\\Program Files\\Java\\jdk1.8.0_31\\jre\\bin\";D:\\Program Files\\apache-maven-3.3.9-bin\\apache-maven-3.3.9\\bin;C:\\Program Files\\cmder;;D:\\Program Files\\Oxygen;",
        "HOMEDRIVE": "C:",
        "PROCESSOR_REVISION": "4e03",
        "=C:": "C:\\",
        "USERDOMAIN": "SC-201802012049",
        "ALLUSERSPROFILE": "C:\\ProgramData",
        "ProgramW6432": "C:\\Program Files",
        "PROCESSOR_IDENTIFIER": "Intel64 Family 6 Model 78 Stepping 3, GenuineIntel",
        "SESSIONNAME": "Console",
        "FPS_BROWSER_USER_PROFILE_STRING": "Default",
        "TMP": "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp",
        "PROCESSOR_ARCHITECTURE": "AMD64",
        "CommonProgramFiles": "C:\\Program Files\\Common Files",
        "CLASSPATH": ".;C:\\Program Files\\Java\\jdk1.8.0_31\\lib;C:\\Program Files\\Java\\jdk1.8.0_31\\lib\\tools.jar",
        "=::": "::\\",
        "LOGONSERVER": "\\\\SC-201802012049",
        "M2_HOME": "D:\\Program Files\\apache-maven-3.3.9-bin\\apache-maven-3.3.9",
        "OS": "Windows_NT",
        "HOMEPATH": "\\Users\\Administrator",
        "PROCESSOR_LEVEL": "6",
        "FPS_BROWSER_APP_PROFILE_STRING": "Internet Explorer",
        "CommonProgramW6432": "C:\\Program Files\\Common Files",
        "USERDOMAIN_ROAMINGPROFILE": "SC-201802012049",
        "LOCALAPPDATA": "C:\\Users\\Administrator\\AppData\\Local",
        "COMPUTERNAME": "SC-201802012049",
        "windir": "C:\\Windows",
        "SystemRoot": "C:\\Windows",
        "asl.log": "Destination=file",
        "NUMBER_OF_PROCESSORS": "4",
        "USERNAME": "Administrator",
        "PUBLIC": "C:\\Users\\Public",
        "PSModulePath": "C:\\Program Files\\WindowsPowerShell\\Modules;C:\\Windows\\system32\\WindowsPowerShell\\v1.0\\Modules",
        "CommonProgramFiles(x86)": "C:\\Program Files (x86)\\Common Files",
        "ComSpec": "C:\\Windows\\system32\\cmd.exe",
        "ChocolateyInstall": "C:\\ProgramData\\chocolatey",
        "APPDATA": "C:\\Users\\Administrator\\AppData\\Roaming"
    },
    "applicationConfig: [classpath:/application.yml]": {
        "server.port": 80,
        "management.security.enabled": false,
        "management.port": 80,
        "management.context-path": "/monitor",
        "endpoints.shutdown.enabled": true
    }
}
```



### health

查看所在应用的健康状态， 如磁盘、数据源、Redis 、Elasticsearch等。健康状态分为UP（正常）和DOWN（故障）状态。访问http://localhost/monitor/health，显示如下：

```
{
    "status": "UP",
    "diskSpace": {
        "status": "UP",
        "total": 107380994048,
        "free": 63853707264,
        "threshold": 10485760
    }
}
```



### mappings

输出所有通过注解`＠RequestMapping`设置的URL映射，可以通过此来查看URL对应的Controller。访问http://localhost/monitor/mappings，显示如下：

```
{
    "/webjars/**": {
        "bean": "resourceHandlerMapping"
    },
    "/**": {
        "bean": "resourceHandlerMapping"
    },
    "/**/favicon.ico": {
        "bean": "faviconHandlerMapping"
    },
    "{[/]}": {
        "bean": "requestMappingHandlerMapping",
        "method": "java.lang.String com.springboot.demo.DemoApplication.index()"
    },
    "{[/error]}": {
        "bean": "requestMappingHandlerMapping",
        "method": "public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)"
    },
    "{[/error],produces=[text/html]}": {
        "bean": "requestMappingHandlerMapping",
        "method": "public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)"
    },
    "{[/monitor/loggers/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.LoggersMvcEndpoint.get(java.lang.String)"
    },
    "{[/monitor/loggers/{name:.*}],methods=[POST],consumes=[application/vnd.spring-boot.actuator.v1+json || application/json],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.LoggersMvcEndpoint.set(java.lang.String,java.util.Map<java.lang.String, java.lang.String>)"
    },
    "{[/monitor/loggers || /monitor/loggers.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/shutdown || /monitor/shutdown.json],methods=[POST],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.ShutdownMvcEndpoint.invoke()"
    },
    "{[/monitor/trace || /monitor/trace.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/heapdump || /monitor/heapdump.json],methods=[GET],produces=[application/octet-stream]}": {
        "bean": "endpointHandlerMapping",
        "method": "public void org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint.invoke(boolean,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.io.IOException,javax.servlet.ServletException"
    },
    "{[/monitor/autoconfig || /monitor/autoconfig.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/beans || /monitor/beans.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/mappings || /monitor/mappings.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/health || /monitor/health.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint.invoke(javax.servlet.http.HttpServletRequest,java.security.Principal)"
    },
    "{[/monitor/metrics/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.MetricsMvcEndpoint.value(java.lang.String)"
    },
    "{[/monitor/metrics || /monitor/metrics.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/dump || /monitor/dump.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/env/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EnvironmentMvcEndpoint.value(java.lang.String)"
    },
    "{[/monitor/env || /monitor/env.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/info || /monitor/info.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/configprops || /monitor/configprops.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    "{[/monitor/auditevents || /monitor/auditevents.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}": {
        "bean": "endpointHandlerMapping",
        "method": "public org.springframework.http.ResponseEntity<?> org.springframework.boot.actuate.endpoint.mvc.AuditEventsMvcEndpoint.findByPrincipalAndAfterAndType(java.lang.String,java.util.Date,java.lang.String)"
    }
}
```



### metrics

显示Spring Boot的性能指标，如己有内存、未占用内存、垃圾回收次数、类信息等。访问http://localhost/monitor/metrics，输出如下：

```
{
    "mem": 245361,
    "mem.free": 76255,
    "processors": 4,
    "instance.uptime": 15927377,
    "uptime": 15932920,
    "systemload.average": -1.0,
    "heap.committed": 209408,
    "heap.init": 122659,
    "heap.used": 133152,
    "heap": 1745920,
    "nonheap.committed": 36480,
    "nonheap.init": 24000,
    "nonheap.used": 35953,
    "nonheap": 133120,
    "threads.peak": 24,
    "threads.daemon": 20,
    "threads.totalStarted": 27,
    "threads": 22,
    "classes": 6107,
    "classes.loaded": 6107,
    "classes.unloaded": 0,
    "gc.ps_scavenge.count": 5,
    "gc.ps_scavenge.time": 73,
    "gc.ps_marksweep.count": 0,
    "gc.ps_marksweep.time": 0,
    "httpsessions.max": -1,
    "httpsessions.active": 0,
    "counter.status.200.monitor.autoconfig": 2,
    "counter.status.200.monitor.beans": 2,
    "counter.status.200.monitor.configprops": 1,
    "counter.status.200.monitor.dump": 4,
    "counter.status.200.monitor.env": 1,
    "counter.status.200.monitor.health": 1,
    "counter.status.200.monitor.info": 1,
    "counter.status.200.monitor.loggers": 1,
    "counter.status.200.monitor.mappings": 1,
    "counter.status.200.monitor.trace": 3,
    "counter.status.200.star-star.favicon.ico": 3,
    "counter.status.404.star-star": 2,
    "gauge.response.monitor.autoconfig": 14.0,
    "gauge.response.monitor.beans": 42.0,
    "gauge.response.monitor.configprops": 440.0,
    "gauge.response.monitor.dump": 62.0,
    "gauge.response.monitor.env": 14.0,
    "gauge.response.monitor.health": 22.0,
    "gauge.response.monitor.info": 26.0,
    "gauge.response.monitor.loggers": 237.0,
    "gauge.response.monitor.mappings": 7.0,
    "gauge.response.monitor.trace": 8.0,
    "gauge.response.star-star": 12.0,
    "gauge.response.star-star.favicon.ico": 6.0
}
```



对`/metrics`接口提供的信息进行简单分类如下表：

| 分类        | 前缀                                                    | 报告内容                                                     |
| :---------- | :------------------------------------------------------ | :----------------------------------------------------------- |
| 垃圾收集器  | gc.                                                     | 已经发生过的垃圾收集次数，以及垃圾收集所耗费的时间，适用于标记-清理垃圾收集器和并行垃圾收集器(数据源自java.lang.management. GarbageCollectorMXBean) |
| 内存        | mem.                                                    | 分配给应用程序的内存数量和空闲的内存数量(数据源自java.lang. Runtime) |
| 堆          | heap.                                                   | 当前内存用量(数据源自java.lang.management.MemoryUsage)       |
| 类加载器    | classes.                                                | JVM类加载器加载与卸载的类的数量(数据源自java.lang. management.ClassLoadingMXBean) |
| 系统        | processors、instance.uptime、uptime、systemload.average | 系统信息，例如处理器数量(数据源自java.lang.Runtime)、运行时间(数据源自java.lang.management.RuntimeMXBean)、平均负载(数据源自java.lang.management.OperatingSystemMXBean) |
| 线程池      | thread.                                                 | 线程、守护线程的数量，以及JVM启动后的线程数量峰值(数据源自 java.lang .management.ThreadMXBean) |
| 数据源      | datasource.                                             | 数据源连接的数量(源自数据源的元数据，仅当Spring应用程序上下文里存在 DataSource Bean 的时候才会有这个信息) |
| Tomcat 会话 | httpsessions.*                                          | Tomcat的活跃会话数和最大会话数(数据源自嵌入式Tomcat的Bean，仅在使用嵌入式Tomcat服务器运行应用程序时才有这个信息) |
| HTTP        | counter.status.*、gauge.response.*                      | 多种应用程序服务HTTP请求的度量值与计数器                     |

HTTP的计数器和度量值需要做一点说明。`counter.status`后的值是HTTP状态码，随后是所请求的路径。举个例子，`counter.status.200.metrics` 表明`/metrics`端点返回 200(OK) 状态码的次数。

HTTP的度量信息在结构上也差不多，却在报告另一类信息。它们全部以`gauge.response`开头，表明这是HTTP响应的度量信息。前缀后是对应的路径。度量值是以毫秒为单位的时间，反映了最近处理该路径请求的耗时。

这里还有几个特殊的值需要注意。root路径指向的是根路径或`/`。`star-star`代表了那些Spring认为是静态资源的路径，包括图片、JavaScript和样式表，其中还包含了那些找不到的资源。这就是为什么你经常会看到`counter.status.404.star-star`，这是返回了`HTTP 404 (NOT FOUND)`状态的请求数。

`/metrics`接口会返回所有的可用度量值，但你也可能只对某个值感兴趣。要获取单个值，请求时可以在URL后加上对应的键名。例如，要查看空闲内存大小,可以向`/metrics/mem.free`发一个GET请求。

## 定制Actuator

### 修改接口ID

每个Actuator接口都有一个ID用来决定接口的路径，比方说，`/beans`接口的默认ID就是beans。比如要修改`/beans`为 `/instances`，则设置如下：

```
endpoints:
  beans:
    id: instances
```



### 启用和禁用接口

虽然Actuator的接口都很有用，但你不一定需要全部这些接口。默认情况下，所有接口（除了`/shutdown`）都启用。比如要禁用 `/metrics` 接口，则可以设置如下：

```
endpoints:
  metrics:
    enabled: false
```



如果你只想打开一两个接口，那就先禁用全部接口，然后启用那几个你要的，这样更方便。

```
endpoints:
  enabled: false
  metrics:
    enabled: true
```
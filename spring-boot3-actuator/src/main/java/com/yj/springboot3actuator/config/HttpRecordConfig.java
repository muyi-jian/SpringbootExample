package com.yj.springboot3actuator.config;


/**
 * @author YangJian
 * @version 1.0.0
 * @title HttpRecordConfig
 * @create 2025/6/20 10:33
 * @description <TODO description class purpose>
 */

// @Configuration
public class HttpRecordConfig {

    /**
     * 创建并配置一个内存中的HTTP交换记录存储库
     *
     * 该方法定义了一个Spring的@Bean注解，用于创建一个InMemoryHttpExchangeRepository实例
     * 这个实例用于存储HTTP请求和响应的交换记录，以便于调试和监控
     *
     * @return InMemoryHttpExchangeRepository 返回配置好的HTTP交换记录存储库实例
     */
    // @Bean
    // InMemoryHttpExchangeRepository httpExchangeRepository() {
    //     // 创建一个内存中的HTTP交换记录存储库实例
    //     InMemoryHttpExchangeRepository repository = new InMemoryHttpExchangeRepository() ;
    //
    //     // 设置存储库的容量为20条记录，超过容量时会根据设置的存储策略进行覆盖
    //     repository.setCapacity(20) ;
    //
    //     // 设置存储库以相反的顺序存储记录，最新的记录会放在最前面
    //     repository.setReverse(true) ;
    //
    //     // 返回配置好的HTTP交换记录存储库实例
    //     return repository ;
    // }
}
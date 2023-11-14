package spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import spring.boot.resolver.MyLocaleResolver;

/**
 * @author yangjian
 * @date 2022/11/23 18:43
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 为了方便测试设置默认页面为首页-i18n
     * @param registry
     * @return
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("i18n");
        registry.addViewController("/i18n.html").setViewName("i18n");
    }




    /**
     * 注册国际化组件-使用自定义LocaleResolver
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 使用SessionLocaleResolver 、cookie
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    /**
     * 拦截器
     * @param registry
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加locale拦截器
        registry.addInterceptor(localeChangeInterceptor());
    }
    /**
     * 使用自定义SessionLocaleResolver
     * @return
     */
    /*@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }*/
    /**
     * 使用CookieLocaleResolver
     * @return
     */
    /*@Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("localeCookie");
        //设置默认区域
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        //设置cookie有效期.
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }*/


}

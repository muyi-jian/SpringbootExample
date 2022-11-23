package spring.boot.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author yangjian
 * @date 2022/11/23 18:47
 */
@Slf4j
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String language = request.getParameter("language");
        //如果没有获取到就使用默认的
        Locale locale = Locale.getDefault();
        //如果请求不为空
        if (!StringUtils.isEmpty(language)) {
            // zh_CN
            String[] split = language.split("_");
            log.info("=={}",split);
            //语言，国家
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

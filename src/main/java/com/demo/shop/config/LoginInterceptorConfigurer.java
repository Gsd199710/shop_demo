package com.demo.shop.config;

import com.demo.shop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 *  拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**
     *  配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置一个白名单集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/users/regdit");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        //完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);//表示要拦截的url
    }
}

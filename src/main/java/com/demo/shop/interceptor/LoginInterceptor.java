package com.demo.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否含有uid数据，有则通过否则重定向到登录界面
     * @param request
     * @param response
     * @param handler
     * @return 返回值为true时放行当前请求，为false则拦截该请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //HttpServletRequest对象获取全局session对象
        Object obj =  request.getSession().getAttribute("uid");
        if (obj == null){//表示用户没有登录过，则需要重定向会登录页面
            response.sendRedirect("/web/login.html");
            return false;
        }
        //请求放行
        return true;
    }
}

package com.demo.shop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//将当前类对象创建使用维护交给Spring容器
@Aspect//将当前类作为切面类
public class TimerAspect {
    @Around("execution(* com.demo.shop.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        Long end = System.currentTimeMillis();
        System.out.println("耗时为："+(end-start));
        return proceed;
    }
}

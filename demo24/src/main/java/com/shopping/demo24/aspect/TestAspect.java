package com.shopping.demo24.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TestAspect {

//    @Pointcut("execution(* com.shopping.demo24.eda.*.process(..))")
    @Pointcut("execution(* com.shopping.demo24.component.*.test(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(this.getClass().getSimpleName()+"-around:before");
        Object result=joinPoint.proceed();
//        int a=3/0;
        System.out.println(this.getClass().getSimpleName()+"-around:after");
        return result;
    }

}

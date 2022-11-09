package com.shopping.common.log.log4method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log4MethodAspect {

    @Pointcut("@annotation(com.shopping.common.log.log4method.Log4Method)")
    public void log4Method(){};

    @Around("log4Method()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        System.out.println("进入方法");
        result=joinPoint.proceed();
        System.out.println("方法返回");
        return result;
    }

}

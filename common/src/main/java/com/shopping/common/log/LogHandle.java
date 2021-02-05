package com.shopping.common.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogHandle {

    @Pointcut("@annotation(com.shopping.common.log.Log4Method)")
    public void log4Method(){};

    @Around("log4Method()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        System.out.println("进入方法");
        result=joinPoint.proceed();
        System.out.println("方法返回");
        return result;
    }

}

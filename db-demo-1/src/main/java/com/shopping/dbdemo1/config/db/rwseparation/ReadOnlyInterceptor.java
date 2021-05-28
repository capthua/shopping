package com.shopping.dbdemo1.config.db.rwseparation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
//@Component
@Slf4j
@Order(0)
public class ReadOnlyInterceptor {

    @Around("@annotation(readOnly)")
    public Object setRead(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable{
        try{
            DbContextHolder.setDbOpType(DbContextHolder.READ);
            return joinPoint.proceed();
        }finally {
            DbContextHolder.clearDbOpType();
        }
    }

}

package com.shopping.common.aop.interceptor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MethodInterceptorProcessor {

    public static void addInterceptor(AbstractMethodInterceptor interceptor, Object target){
        Advised advised=(Advised) target;
        Advisor myAdvice = new DefaultPointcutAdvisor(interceptor);
        advised.addAdvisor(myAdvice);
    }

}

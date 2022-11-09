package com.shopping.common.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public abstract class AbstractMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("方法调用前");
        Object rval = invocation.proceed();
        bizProcess();
        System.out.println("方法调用后");
        return rval;
    }

    protected abstract Object bizProcess(Object... params);

}

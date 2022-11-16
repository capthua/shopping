package com.shopping.common.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

public class PRSMybatisInterceptor implements Interceptor {

    Boolean needBreak = false;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = before(invocation);
        if (needBreak) {
            return result;
        }
        result = invocation.proceed();
        result = after(result, invocation);
        return result;
    }

    protected Object before(Invocation invocation) throws Throwable {
        return null;
    }

    protected Object after(Object result, Invocation invocation) throws Throwable {
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

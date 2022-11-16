package com.shopping.common.spring;

import org.springframework.aop.framework.Advised;

public class ProxyUtils {
    public static Object getTarget(Object bean) {
        if (bean instanceof Advised) {
            return ((Advised) bean).getTargetSource();
        } else {
            return bean;
        }
    }
}

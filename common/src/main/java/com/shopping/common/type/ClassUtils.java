package com.shopping.common.type;

import java.lang.reflect.Method;

public class ClassUtils {

    public static Method[] getMethods(Class clazz){
        return clazz.getMethods();
    }

    public static Method[] getDeclaredMethods(Class clazz){
        return clazz.getDeclaredMethods();
    }

}

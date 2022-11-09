package com.shopping.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtils {

    /**
     * 这个方法在注解中的类没有加载时会报错
     * @param clazz
     * @return
     */
    public static Annotation[] getAnnotations(Class clazz) {
        return clazz.getAnnotations();
    }

    public static Annotation getAnnotation(Method method, Class annotationType){
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(method,annotationType);
    }

    public static Annotation getAnnotation(Class clazz, Class annotationType){
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(clazz,annotationType);
    }

}

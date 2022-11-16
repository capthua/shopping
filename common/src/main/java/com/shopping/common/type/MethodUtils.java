package com.shopping.common.type;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodUtils {
    public static Annotation findAnnotation(Method method, Class annotationType) {
        return AnnotationUtils.findAnnotation(method, annotationType);
    }
}

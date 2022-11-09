package com.shopping.common.spring;

import com.shopping.common.annotation.AnnotationUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpringUtils {


    public static Class getSpringApplicationClass() {
        Class springApplicationClass = null;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = stackTraceElements.length - 1; i >= 0; i--) {
            StackTraceElement stackTraceElement = stackTraceElements[i];
            Class clazz = null;
            try {
                clazz = Class.forName(stackTraceElement.getClassName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("获取spring启动类出错");
            }
            Annotation[] annotations = clazz.getAnnotations();
            boolean find = false;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(SpringBootApplication.class)) {
                    springApplicationClass = clazz;
                    find = true;
                    break;
                }
            }
            if (find) break;
        }
        if (springApplicationClass == null) {
            throw new RuntimeException("spring启动类不存在");
        }
        return springApplicationClass;
    }

    public static String[] getBasePackages() {
        List<String> basePackages = new ArrayList<>();
        Class springApplicationClass = getSpringApplicationClass();
        basePackages.add(springApplicationClass.getPackage().getName());
        Annotation annotation = AnnotationUtils.getAnnotation(springApplicationClass, ComponentScan.class);
        if (annotation != null) {
            Collections.addAll(basePackages, ((ComponentScan) annotation).basePackages());
        }
        return basePackages.toArray(new String[0]);
    }
}

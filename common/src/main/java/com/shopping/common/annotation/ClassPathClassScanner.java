package com.shopping.common.annotation;

import org.springframework.asm.ClassReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClassPathClassScanner {

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private ResourcePatternResolver resourcePatternResolver;

    public Set<Class> scanClassesWithAnnotation(String[] basePackages, Class annotationType){
         Set<Class> classes=scanClasses(basePackages);
         Set<Class> controllers=new HashSet<>();
         for(Class clazz:classes){
             if(AnnotationUtils.getAnnotation(clazz,annotationType)!=null){
                 controllers.add(clazz);
             }
         }
         return controllers;
    }

    public Set<Class> scanClasses(String... basePackages){
        Set<Class> classes=new HashSet<>();
        Set<Resource> resources = scanResources(basePackages);
        for(Resource resource:resources){
            ClassReader classReader=getClassReader(resource);
            Class clazz;
            try {
                clazz = ClassUtils.forName(classReader.getClassName().replace("/","."),
                        Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("无法加载类: "+classReader.getClassName());
            }
            classes.add(clazz);
        }
        return classes;
    }

    private Set<Resource> scanResources(String... basePackages) {
        Set<Resource> resourceSet=new HashSet<>();
        for(String basePackage:basePackages){
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
            Resource[] resources;
            try {
                resources = getResourcePatternResolver().getResources(packageSearchPath);
            } catch (IOException e) {
                throw new RuntimeException("I/O failure during resource scanning");
            }
            Collections.addAll(resourceSet, resources);
        }
        return resourceSet;
    }

    private ResourcePatternResolver getResourcePatternResolver() {
        if (this.resourcePatternResolver == null) {
            this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
        }
        return this.resourcePatternResolver;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(basePackage);
    }

    private static ClassReader getClassReader(Resource resource){
        InputStream is;
        ClassReader classReader;
        try {
            is = resource.getInputStream();
            classReader =new ClassReader(is);
        } catch (IOException e) {
            throw new RuntimeException("I/O failure during classreader getting");
        }
        return classReader;
    }

}

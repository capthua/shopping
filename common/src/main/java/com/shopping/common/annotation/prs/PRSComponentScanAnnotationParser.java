package com.shopping.common.annotation.prs;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.List;
public class PRSComponentScanAnnotationParser {

    private TypeFilter prsTypeFilter;
    private BeanDefinitionRegistry registry;

    public PRSComponentScanAnnotationParser(BeanDefinitionRegistry registry) {
        prsTypeFilter=new AnnotationTypeFilter(PRS.class);
        this.registry=registry;
    }

    int parse(){
        List<String> basePackages = AutoConfigurationPackages.get((BeanFactory) registry);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(prsTypeFilter);
        return scanner.scan(basePackages.toArray(new String[0]));
    }



}

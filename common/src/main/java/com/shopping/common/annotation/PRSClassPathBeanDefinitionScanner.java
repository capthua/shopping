package com.shopping.common.annotation;

import com.shopping.common.beans.support.BeanDefinitionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class PRSClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    PRSClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                ScopeMetadata scopeMetadata = new AnnotationScopeMetadataResolver().resolveScopeMetadata(candidate);
                candidate.setScope(scopeMetadata.getScopeName());
                String beanName = new AnnotationBeanNameGenerator().generateBeanName(candidate, getRegistry());
                if (candidate instanceof AbstractBeanDefinition) {
                    postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
                }
                if (candidate instanceof AnnotatedBeanDefinition) {
                    AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
                }
                if (checkCandidate(beanName, candidate)) {
                    BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
                    definitionHolder =
                            AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata,
                                    definitionHolder, getRegistry());
                    beanDefinitions.add(definitionHolder);
                }
            }
        }
        registerPRSBeanDefinition(beanDefinitions, getRegistry());
        return beanDefinitions;
    }

    private void registerPRSBeanDefinition(Set<BeanDefinitionHolder> pRSBeanDefinitionHolders, BeanDefinitionRegistry registry) {

        for (BeanDefinitionHolder prsBeanHolder : pRSBeanDefinitionHolders) {
            //如果配置了spring内置扫描的注解的话, 直接跳过
            if (registry.containsBeanDefinition(prsBeanHolder.getBeanName())) {
                continue;
            }
            Class prsClass;
            try {
                prsClass = Class.forName(prsBeanHolder.getBeanDefinition().getBeanClassName());
            } catch (ClassNotFoundException e) {
                logger.error("{} not find", prsBeanHolder.getBeanDefinition().getBeanClassName());
                throw new RuntimeException(prsBeanHolder.getBeanDefinition().getBeanClassName() +
                        " not find");
            }
            String beanName = null;
            //beanDefinitionHolder是扫描的带PRS注解的,所以注解至少有一个
            for (Annotation annotation : prsClass.getAnnotations()) {
                if (annotation instanceof PRS) {
                    PRS prs = (PRS) annotation;
                    beanName = prs.value();
                    break;
                }
            }
            if (StringUtils.isEmpty(beanName)) {
                beanName = prsBeanHolder.getBeanName();
            }
            if (!registry.containsBeanDefinition(beanName)) {
                BeanDefinitionUtils.registerBeanDefinition(beanName,prsBeanHolder,registry);
            }
        }
    }

}

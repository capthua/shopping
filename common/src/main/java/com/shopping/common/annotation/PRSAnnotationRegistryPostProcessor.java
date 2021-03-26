package com.shopping.common.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PRSAnnotationRegistryPostProcessor implements PriorityOrdered, BeanDefinitionRegistryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        PRSClassPathBeanDefinitionScanner prsScanner =
                new PRSClassPathBeanDefinitionScanner(registry, false);
        TypeFilter prsTypeFilter = (metadataReader, metadataReaderFactory) -> {
            boolean match = false;
            if (metadataReader.getClassMetadata().isConcrete() && metadataReader.getAnnotationMetadata().hasAnnotation(PRS.class.getName())) {
                match = true;
            }
            return match;
        };
        List<String> basePackages = AutoConfigurationPackages.get((BeanFactory) registry);
        prsScanner.addIncludeFilter(prsTypeFilter);
        prsScanner.doScan(basePackages.toArray(new String[0]));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    /**
     * 为bean注册别名
     *
     * @param factory
     * @param beanName
     * @param alias
     */
    private void registerAlias(ConfigurableListableBeanFactory factory, String beanName,
                               String alias) {
        if (factory.containsBeanDefinition(alias)) {
            if (logger.isDebugEnabled()) {
                logger.debug("{} is existed", alias);
            }
        } else {
            factory.registerAlias(beanName, alias);
            if (logger.isDebugEnabled()) {
                logger.debug("beanName:{}, alias:{}", beanName, alias);
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

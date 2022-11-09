package com.shopping.common.annotation.prs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PRSAnnotationRegistryPostProcessor implements PriorityOrdered, BeanDefinitionRegistryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        new PRSComponentScanAnnotationParser(registry).parse();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("PRSAnnotationRegistryPostProcessor.postProcessBeanFactory()");
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
        if (!factory.containsBeanDefinition(alias)) {
            factory.registerAlias(beanName, alias);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

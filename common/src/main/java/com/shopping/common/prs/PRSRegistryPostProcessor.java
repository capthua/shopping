package com.shopping.common.prs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import java.lang.annotation.Annotation;

public class PRSRegistryPostProcessor implements PriorityOrdered, BeanDefinitionRegistryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if(logger.isDebugEnabled()){
            logger.debug("postProcessBeanFactory");
        }
        String[] prsBeans=beanFactory.getBeanNamesForAnnotation(PRS.class);
        for(String prsBean:prsBeans){
            Class<?> prsClazz=beanFactory.getType(prsBean);
            if(prsClazz.getAnnotations()!=null&&prsClazz.getAnnotations().length>0){
                for(Annotation annotation:prsClazz.getAnnotations()){
                    if(annotation instanceof PRS){
                        PRS prs=(PRS) annotation;
                        registerAlias(beanFactory,prsBean,prsClazz.getName());
                        //其他别名注册
                        for(String row:prs.value()){
                            registerAlias(beanFactory,prsBean,row);
                        }
                    }
                }
            }
        }

    }

    /**
     * 为bean注册别名
     * @param factory
     * @param beanName
     * @param alias
     */
    private void registerAlias(ConfigurableListableBeanFactory factory,String beanName,
                               String alias){
        if(factory.containsBeanDefinition(alias)){
            if(logger.isDebugEnabled()){
                logger.debug("[failed] because bean:{} is existed",alias);
            }
        } else {
            factory.registerAlias(beanName,alias);
            if(logger.isDebugEnabled()){
                logger.debug("[success] beanName:{}, alias:{}",beanName,alias);
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

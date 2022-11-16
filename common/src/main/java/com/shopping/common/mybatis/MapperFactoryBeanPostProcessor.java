package com.shopping.common.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.mapper.MapperFactoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author CapHua
 */
//@Component
public class MapperFactoryBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MapperFactoryBean) {
            MapperFactoryBean mapperFactoryBean = (MapperFactoryBean) bean;
            Type[] types = mapperFactoryBean.getMapperInterface().getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType t = (ParameterizedType) type;
                    Class<?> returnType = (Class<?>) t.getActualTypeArguments()[0];
                    JpaAnnotationUtils.addAnnotation(returnType);
                }
            }
        }
        return bean;
    }
}

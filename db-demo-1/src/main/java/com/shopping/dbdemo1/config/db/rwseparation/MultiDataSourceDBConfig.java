package com.shopping.dbdemo1.config.db.rwseparation;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class MultiDataSourceDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        ReadWriteRoutingDataSource proxy = new ReadWriteRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DbContextHolder.WRITE, writeDataSource());
        targetDataSources.put(DbContextHolder.READ, readDataSource());
        proxy.setDefaultTargetDataSource(writeDataSource());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    /**
     * 将路由数据源设置到SqlSessionFactoryBean
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(MybatisProperties properties) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(routingDataSource());
        sessionFactoryBean.setConfiguration(properties.getConfiguration());
        return sessionFactoryBean.getObject();
    }

    /**
     * 将路由数据源设置到事务管理中
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager transactionManager=
                new DataSourceTransactionManager(routingDataSource());
        return transactionManager;
    }

}

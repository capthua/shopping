package com.shopping.demo24;


import com.shopping.common.annotation.prs.PRS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCaching
//@EnableTransactionManagement(order = 1)
@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.shopping.demo24.dao")
@ComponentScan(basePackages = {"com.shopping"})
public class Demo24Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Demo24Application.class, args);
        //context.close();
    }
}

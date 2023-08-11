package com.shopping.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author hanshaohua
 * @date 2019/11/01
 */

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.shopping.user.dao.mapper")
@EnableDubbo(scanBasePackages = "com.shopping.*.service.rpc")
@ComponentScan(basePackages = {"com.shopping"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}


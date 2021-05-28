package com.shopping.dbdemo1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableTransactionManagement(order = 1)
@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.shopping.dbdemo1.dao")
public class DbDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(DbDemo1Application.class, args);
    }
}

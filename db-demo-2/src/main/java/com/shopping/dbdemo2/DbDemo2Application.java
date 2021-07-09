package com.shopping.dbdemo2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.shopping.dbdemo2.dao")
public class DbDemo2Application {
    public static void main(String[] args) {
        SpringApplication.run(DbDemo2Application.class, args);
    }
}

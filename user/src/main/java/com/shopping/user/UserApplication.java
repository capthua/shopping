package com.shopping.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */

@SpringBootApplication
@EnableDiscoveryClient
//mapperscan必须加
@MapperScan("com.shopping.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}


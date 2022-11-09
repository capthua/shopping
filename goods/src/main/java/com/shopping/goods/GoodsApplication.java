package com.shopping.goods;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.shopping.goods.dao.mapper")
@EnableDubbo(scanBasePackages = "com.shopping.*.service.rpc")
@ComponentScan(basePackages = {"com.shopping"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}

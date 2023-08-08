package com.shooping.cart;


//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {"com.shopping"})
@MapperScan("com.shopping.cart.dao")
public class CartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CartApplication.class, args);
    }
}

package com.shopping.demo24.configuration;

import com.shopping.demo24.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration implements ApplicationRunner, CommandLineRunner {

    @Bean
    public OrderVO orderVO() {
        return new OrderVO();
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("hehe");
    }

    @Override
    public void run(String... args) {
        System.out.println("hehe");
    }
}

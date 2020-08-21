package com.shopping.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */

@SpringBootApplication
@EnableDiscoveryClient
//mapperscan必须加 mybatis要么扫描包，要么加@Mapper
@MapperScan("com.shopping.samples.dao")
public class SamplesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SamplesApplication.class, args);
    }
}


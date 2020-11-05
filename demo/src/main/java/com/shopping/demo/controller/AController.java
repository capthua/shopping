package com.shopping.demo.controller;

import com.shopping.common.spring.ApplicationContextUtils;
import com.shopping.demo.service.RedisSampleService;
import com.shopping.demo.service.RedissonSampleService;
import io.prs.mybatisx.autoconfigure.MybatisxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("a")
public class AController {

    @Autowired
    MybatisxTemplate template;

    @Autowired
    ApplicationContextUtils applicationContextUtils;

    @Autowired
    RedisSampleService redisSampleService;
    @Autowired
    RedissonSampleService redissonSampleService;


    @GetMapping("a")
    public String a(){
//        redisSampleService.setSamples();
//        redisSampleService.hashSamples();
//        redisSampleService.zSetSamples();
        redissonSampleService.redissonSamples();
        return template.getProperties().toString();
    }

}

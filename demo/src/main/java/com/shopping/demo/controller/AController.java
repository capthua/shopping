package com.shopping.demo.controller;

import com.shopping.common.spring.ApplicationContextUtils;
import com.shopping.demo.service.RedisSampleService;
import com.shopping.demo.service.RedissonSampleService;
import io.prs.mybatisx.autoconfigure.MybatisxTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("a")
public class AController {

    private static final Logger logger= LoggerFactory.getLogger(AController.class);

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
//        redissonSampleService.redissonSamples();
        return template.getProperties().toString();
    }

    @GetMapping("logtest")
    public String logtest()throws Exception{
        logger.debug("debug");
        logger.info("info");

//        for(int i=0;i<500000;i++){
//            if(i%10000==0){
//                TimeUnit.SECONDS.sleep(2);
//            }
//            logger.info("info");
//        }
        return template.getProperties().toString();
    }

}

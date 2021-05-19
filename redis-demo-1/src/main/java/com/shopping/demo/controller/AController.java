package com.shopping.demo.controller;

import com.shopping.common.spring.ApplicationContextUtils;
import com.shopping.demo.prs.Male;
import com.shopping.demo.service.AopTestService;
import com.shopping.demo.service.ConcurrentCollectionService;
import com.shopping.demo.service.RedisSampleService;
import com.shopping.demo.service.RedissonSampleService;
import io.prs.mybatisx.autoconfigure.MybatisxTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
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
    @Autowired
    ConcurrentCollectionService concurrentCollectionService;
    @Autowired
    AopTestService aopTestService;

    @Autowired
    Male male;

    @GetMapping("log4methodTest")
    public String log4methodTest(){
        logger.debug("这是日志测试");
        Advised advised=(Advised) aopTestService;
        Advisor[] advisors=advised.getAdvisors();
        aopTestService.log4MethodTest("hehe");
        return "hehe";
    }

    @GetMapping("redisTest")
    public String a(){
        redisSampleService.setSamples();
//        redisSampleService.hashSamples();
//        redisSampleService.zSetSamples();
//        redissonSampleService.redissonSamples();
        return template.getProperties().toString();
    }

    @GetMapping("collectionTest")
    public String testCollection(String key,String value){
        concurrentCollectionService.testCHashMap(key,value);
        return "hehe";
    }

    @GetMapping("logtest")
    public String logtest()throws Exception{
        String hehe=null;
        logger.error("hehe{}",hehe);
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
//        logger.error("exception:",new IllegalArgumentException("参数错误"));

//        for(int i=0;i<500000;i++){
//            if(i%10000==0){
                TimeUnit.SECONDS.sleep(2);
//            }
//            logger.info("info");
//        }
        return template.getProperties().toString();
    }

}

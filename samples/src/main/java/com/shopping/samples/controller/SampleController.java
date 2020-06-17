package com.shopping.samples.controller;

//import com.shopping.user.events.queuesend.Sender;

import com.shopping.samples.model.Sample;
import com.shopping.samples.service.LampService;
import com.shopping.samples.service.RabbitMqService;
import com.shopping.samples.service.impl.SampleServiceImpl;
import com.shopping.samples.service.impl.TxPropagationSampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
//@Slf4j //自动生成log变量
@RestController
@RequestMapping("smpl")
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private SampleServiceImpl sampleService;

    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private TxPropagationSampleService txPropagationSampleService;

    @Autowired
    private LampService lampService;

    @RequestMapping("test")
    public String test() {
        rabbitMqService.test();
        return "hehe";
    }

    @RequestMapping("testZuul")
    public String testZuul(){
        return "testZuul";
    }

    @RequestMapping("testTrx")
    public String testTrx(String name,String descr){
        name=name+ UUID.randomUUID().toString();
        Date time=new Date();
        Sample sample=Sample.builder().name(name).description(descr).createTime(time).build();
        sampleService.saveSample(sample);
//        txPropagationSampleService.saveSample1(sample);
        return "hehe";
    }

    @RequestMapping("testMqtt")
    public String testMqtt(){
        lampService.turnOn("han");
        return "testMqtt";
    }


}


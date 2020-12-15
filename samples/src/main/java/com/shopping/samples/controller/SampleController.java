package com.shopping.samples.controller;

//import com.shopping.user.events.queuesend.Sender;

import com.shopping.samples.dao.HtT1Mapper;
import com.shopping.samples.model.Sample;
import com.shopping.samples.service.LampService;
import com.shopping.samples.service.RabbitMqService;
import com.shopping.samples.service.RpcSampleService;
import com.shopping.samples.service.SampleService;
import com.shopping.samples.service.TxPropagationSampleService;
import com.shopping.samples.vo.HtT1Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
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
    private SampleService sampleService;

    @Autowired
    private HtT1Mapper htT1Mapper;

    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private TxPropagationSampleService txPropagationSampleService;

    @Autowired
    private RpcSampleService rpcSampleService;

    @Autowired
    private LampService lampService;

    @RequestMapping("testMq")
    public String testMq() {
        rabbitMqService.test();
        return "hehe";
    }

    @RequestMapping("testDb")
    public String testDb() {
        List<Sample> samples = sampleService.list();
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
        Sample sample=new Sample();
        sample.setName("hehe");
        sampleService.saveSample(sample);
//        txPropagationSampleService.saveSample1(sample);
        return "hehe";
    }

    @RequestMapping("testMqtt")
    public String testMqtt(){
        lampService.turnOn("han");
        return "testMqtt";
    }

    @RequestMapping("testMybatis")
    public Object testMybatis(){
        List<HtT1Vo> htT1Vos = htT1Mapper.listDeepChildren(0,"0");
        return htT1Vos;
    }

    @RequestMapping("testRpc")
    public Object testRpc(Integer id){
        return rpcSampleService.getUserById(id);
    }


}


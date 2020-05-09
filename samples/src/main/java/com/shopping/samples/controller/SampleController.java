package com.shopping.samples.controller;

//import com.shopping.user.events.queuesend.Sender;
import com.shopping.samples.service.impl.SampleServiceImpl;
import com.shopping.samples.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("test")
    public String test() {
        rabbitMqService.test();
        return "hehe";
    }

    @RequestMapping("testZuul")
    public String testZuul(){
        return "testZuul";
    }

}


package com.shopping.demo24.controller;

import com.shopping.common.eda.ProcessorManager;
import com.shopping.common.eda.IotMessage;
import com.shopping.demo24.service.MybatisCacheTestService;
import com.shopping.demo24.service.DictionaryService;
import com.shopping.demo24.service.DubboDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Qualifier("msgUp")
    @Autowired
    private ProcessorManager msgUpProcessorManager;

    @Qualifier("msgDown")
    @Autowired
    private ProcessorManager msgDownProcessorManager;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    MybatisCacheTestService cacheTestService;

    @Autowired
    DubboDemoService dubboDemoService;


    @PostMapping("save")
    public String save() {
        String msg="hehe";
        msgUpProcessorManager.process("sensorStatus",msg);
        msgUpProcessorManager.process("gatewayStatus",msg);
        msgDownProcessorManager.process("3",new IotMessage());
        return "hehe";
    }

    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("secondLevelCache")
    public String secondLevelCache() {
       cacheTestService.secondLevelCacheTest();
       return "hehe";
    }

    @GetMapping("dubboDemo")
    public String dubboDemo() {
        dubboDemoService.dubboTest1();
        return "hehe";
    }
}


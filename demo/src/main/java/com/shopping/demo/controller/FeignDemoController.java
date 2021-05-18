package com.shopping.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("feignDemo")
public class FeignDemoController {

    @RequestMapping("zuulTimeout")
    public String zuulTimeout() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        return "hehe";
    }

}

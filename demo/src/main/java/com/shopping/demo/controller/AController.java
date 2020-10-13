package com.shopping.demo.controller;

import com.prs.HelloService;
import com.shopping.common.spring.ApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("a")
public class AController {

    @Autowired
    HelloService helloService;

    @Autowired
    ApplicationContextUtils applicationContextUtils;

    @GetMapping("a")
    public String a(){
        return helloService.sayHello("wwt");
    }

}

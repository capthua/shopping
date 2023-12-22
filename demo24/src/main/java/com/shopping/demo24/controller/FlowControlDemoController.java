package com.shopping.demo24.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.shopping.common.response.BaseResponse;
import com.shopping.common.response.ObjectResponse;
import com.shopping.demo24.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CaptHua
 */
@RestController
@RequestMapping("flowControl")
public class FlowControlDemoController {

    @Autowired
    private TestService testService;

    @GetMapping("sayHello")
    public BaseResponse sayHello(String name) {;
        ObjectResponse<String> response=new ObjectResponse<>();
        response.setData(testService.sayHello(name));
        return response;
    }

    @GetMapping("sayBye")
    public BaseResponse sayBye(String name) {;
        ObjectResponse<String> response=new ObjectResponse<>();
        response.setData(testService.sayBye(name));
        return response;
    }

}

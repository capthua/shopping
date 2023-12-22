package com.shopping.demo24.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.shopping.demo24.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author CaptHua
 */
@Service
public class TestServiceImpl implements TestService {

    @SentinelResource(value = "sayHelloS")
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @SentinelResource(value = "sayByeS")
    @Override
    public String sayBye(String name) {
        return "Bye bye, " + name;
    }
}

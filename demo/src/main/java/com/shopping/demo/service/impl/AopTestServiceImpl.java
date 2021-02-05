package com.shopping.demo.service.impl;

import com.shopping.common.log.Log4Method;
import com.shopping.demo.service.AopTestService;
import org.springframework.stereotype.Service;

@Service
public class AopTestServiceImpl implements AopTestService {

    @Log4Method
    @Override
    public String log4MethodTest(String str) {
        System.out.println(str);
        return str;
    }
}

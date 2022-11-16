package com.shopping.demo24.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
public class TestA {

    @Autowired
    TestB testB;

    public void test() {
        System.out.println("----aaa----");
    }

}

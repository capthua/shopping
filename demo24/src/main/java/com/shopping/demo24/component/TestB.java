package com.shopping.demo24.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestB {

    @Autowired
    TestC testC;

    public void test() {
        System.out.println("----bbb----");
    }
}

package com.shopping.demo24.dubbo.spi.activate;

import org.apache.dubbo.common.extension.Activate;

@Activate(group = {"g1","g3"},order = 1)
public class DemoExtImplC implements DemoExt{
    @Override
    public void say() {
        System.out.println("DemoExtImplA");
    }
}

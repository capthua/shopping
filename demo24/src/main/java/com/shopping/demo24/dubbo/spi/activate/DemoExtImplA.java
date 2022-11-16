package com.shopping.demo24.dubbo.spi.activate;

import org.apache.dubbo.common.extension.Activate;

@Activate(group = {"g1", "g2"}, value = {"k1:v1", "k2,v2"}, order = 2)
public class DemoExtImplA implements DemoExt {
    @Override
    public void say() {
        System.out.println("DemoExtImplA");
    }
}

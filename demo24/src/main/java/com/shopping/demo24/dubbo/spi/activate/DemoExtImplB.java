package com.shopping.demo24.dubbo.spi.activate;

import org.apache.dubbo.common.extension.Activate;

//@Activate(group = {"g1"},value = "k1:v1",order = 3)
@Activate(group = {"g1"}, order = 3)
public class DemoExtImplB implements DemoExt {
    @Override
    public void say() {
        System.out.println("DemoExtImplA");
    }
}

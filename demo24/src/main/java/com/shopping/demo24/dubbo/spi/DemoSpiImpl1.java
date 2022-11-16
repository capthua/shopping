package com.shopping.demo24.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

//@Adaptive
public class DemoSpiImpl1 implements DemoSpi {

    @Override
    public void say(URL url) {
        System.out.println("Hello DemoSpiImpl1");
    }

}

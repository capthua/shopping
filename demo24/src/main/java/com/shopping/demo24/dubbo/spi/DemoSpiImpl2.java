package com.shopping.demo24.dubbo.spi;

import org.apache.dubbo.common.URL;

public class DemoSpiImpl2 implements DemoSpi {

    @Override
    public void say(URL url) {
        System.out.println("Hello DemoSpiImpl2");
    }

}

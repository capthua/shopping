package com.shopping.demo24.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("d2")
public interface DemoSpi {

    @Adaptive({"key"})
    void say(URL url);
}

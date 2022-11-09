package com.shopping.demo24.dubbo.spi.activate;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface DemoExt {
    void say();
}

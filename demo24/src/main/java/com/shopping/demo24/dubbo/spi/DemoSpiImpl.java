package com.shopping.demo24.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

public class DemoSpiImpl implements DemoSpi{

    Property property;
    @Override
    public void say(URL url) {
        System.out.println("Hello DemoSpiImpl");
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}

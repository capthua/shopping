package com.shopping.demo24.dubbo;

import com.shopping.demo24.Demo24Application;
import com.shopping.demo24.dubbo.spi.DemoSpi;
import com.shopping.demo24.dubbo.spi.activate.DemoExt;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo24Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DubboSpiTest {

    /**
     * dubbo spi
     *
     * @throws Exception
     */
    @Test
    public void spi() throws Exception {
        ExtensionLoader<DemoSpi> extensionLoader =
                ExtensionLoader.getExtensionLoader(DemoSpi.class);
        DemoSpi demoSpi = null;
        URL url = URL.valueOf("demo://localhost/demo");

//        //通过扩展名获取扩展
//        demoSpi = extensionLoader.getExtension("d0");
//        demoSpi.say(url);
//        demoSpi=extensionLoader.getExtension("d2");
//        demoSpi.say(url);

//        配置自适应扩展
//        1.设置SPI注解的值为d2，@SPI("d2")
        demoSpi = extensionLoader.getAdaptiveExtension();
        demoSpi.say(url);

        //2. url中的参数k与Adaptive中的参数相同，默认参数为 DemoSpi->demo.spi
        url = URL.valueOf("demo://localhost/demo?demo.spi=d0");
        url = URL.valueOf("demo://localhost/demo?key=d0");
        demoSpi = extensionLoader.getAdaptiveExtension();
        demoSpi.say(url);

        //3.在类DemoSpiImpl1上添加@Adaptive，设置 SPI注解的值为d2，获取的是d1，此时获取的是实现类，而不是生成的类
//        所以 @Adaptive注解的优先级最高
        demoSpi = extensionLoader.getAdaptiveExtension();
        demoSpi.say(url);

//        URL url = URL.valueOf("dubbo://localhost/test");
//        Protocol adaptiveProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
//        adaptiveProtocol.refer(adaptiveProtocol.class, url);
    }

    /**
     * dubbo spi
     *
     * @throws Exception
     */
    @Test
    public void spiActivate() throws Exception {
        ExtensionLoader<DemoExt> loader = ExtensionLoader.getExtensionLoader(DemoExt.class);
        URL url = URL.valueOf("test://localhost/test");

        // 1. 组参数为空，不会用组过滤，只会用value过滤
        List<DemoExt> list = null;
        list = loader.getActivateExtension(url, new String[]{}, null);
        System.out.println(list);
//        [com.shopping.demo24.dubbo.spi.activate.DemoExtImplC@301770d9,
//        com.shopping.demo24.dubbo.spi.activate.DemoExtImplB@40edc64e]

        //2. 组匹配
        list = loader.getActivateExtension(url, new String[]{}, "g1");
        System.out.println(list);
//        [com.shopping.demo24.dubbo.spi.activate.DemoExtImplC@301770d9]

        //3. 组与value，注解中如果设置了value，则要求value中的key:value必须与url中的相同，
        // 注解中如果没有value，则不会校验参数，只用group匹配
        url = url.addParameter("k1", "v1");
        list = loader.getActivateExtension(url, new String[]{}, "g1");
        System.out.println(list);
//        [com.shopping.demo24.dubbo.spi.activate.DemoExtImplC@e1fd2bf,
//        com.shopping.demo24.dubbo.spi.activate.DemoExtImplA@301770d9,
//        com.shopping.demo24.dubbo.spi.activate.DemoExtImplB@40edc64e]
    }

    @Test
    public void spiInternal() throws Exception {
        URL url = URL.valueOf("dubbo://localhost/test");
        Protocol adaptiveProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        adaptiveProtocol.refer(DubboProtocol.class, url);
    }

}

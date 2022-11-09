package com.shopping.demo24.eda;

import com.shopping.common.eda.ProcessorManager;
import com.shopping.common.eda.ProcessorManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdaConfiguration {

    @Bean("msgUp")
    public ProcessorManager msgUp() {
        return new ProcessorManagerImpl();
    }

    @Bean("msgDown")
    public ProcessorManager msgDown() {
        return new ProcessorManagerImpl();
    }


}

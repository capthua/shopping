package com.shopping.demo24.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component
@Slf4j
public class TempTest implements ApplicationRunner, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runThreadPool();
    }

    void runThreadPool() {
        @SuppressWarnings("AlibabaThreadPoolCreation")
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runnable runnable = () -> {
            for (; ; ) {
                try {
                    applicationContext.getBean("22");
                } catch (Exception e) {
                    log.error("error", e);
                }
                System.out.println("我在跑着");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(runnable);
    }

}

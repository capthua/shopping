package com.shopping.common.eda;

import com.shopping.common.annotation.AnnotationUtils;
import com.shopping.common.spring.ProxyUtils;
import com.shopping.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class EdaContext implements ApplicationListener<ContextRefreshedEvent> {


    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.applicationContext = event.getApplicationContext();
        Map<String, Object> processors = applicationContext.getBeansWithAnnotation(EdaEventProcessor.class);
        for (Map.Entry<String, Object> entry : processors.entrySet()) {
            EdaEventProcessor edaEventProcessor = (EdaEventProcessor) AnnotationUtils.getAnnotation(entry.getValue().getClass(),
                    EdaEventProcessor.class);
            String managerName = edaEventProcessor.manager();
            String eventId = edaEventProcessor.eventId();
            String processorName = edaEventProcessor.value();
            log.info("eda----manager:{},eventId:{},processor:{}", managerName, eventId, processorName);
            if (StringUtils.isAnyBlank(managerName, eventId, processorName)) {
                throw new RuntimeException("EdaEventProcessor注解中的值不能为空");
            }
            ProcessorManager manager = applicationContext.getBean(managerName, ProcessorManagerImpl.class);
            Object processorBean = getBean(processorName);
            if (processorBean instanceof EventProcessor) {
                manager.registerProcessor(eventId, (EventProcessor) processorBean);
            } else {
                throw new RuntimeException("bean不是EventProcessor");
            }
        }
    }

    private Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}

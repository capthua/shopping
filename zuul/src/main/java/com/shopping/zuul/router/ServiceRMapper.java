package com.shopping.zuul.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceRMapper implements ServiceRouteMapper {
    @Override
    public String apply(String serviceId) {
        log.debug("serviceId:{}",serviceId);
        return serviceId;
    }
}

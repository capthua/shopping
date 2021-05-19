package com.shopping.demo.service.impl;

import com.shopping.common.redis.template.RedissonTemplate;
import com.shopping.demo.service.RedissonSampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedissonSampleServiceImpl implements RedissonSampleService {

    private static final Logger logger= LoggerFactory.getLogger(RedissonSampleServiceImpl.class);

    @Autowired(required = false)
    RedissonTemplate redissonUtils;

    @Override
    public void redissonSamples() {
        redissonUtils.lock("han_lock");
        logger.info("hehe");
        redissonUtils.unlock("han_lock");
    }
}

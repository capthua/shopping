package com.shopping.common.redis;

import com.shopping.common.redis.template.RedissonTemplate;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(RedissonAutoConfiguration.class)
public class RedissonConfigure {

    @Autowired
    private RedissonClient client;

    /**
     * @ConditionalOnBean(RedissonClient.class) 不起作用, 等待看源码分析原因
     * @return
     */
    @Bean
    //@ConditionalOnBean(RedissonClient.class)
    @ConditionalOnMissingBean(name = "redissonTemplate")
    public RedissonTemplate redissonTemplate(){
        RedissonTemplate redissonTemplate=new RedissonTemplate();
        redissonTemplate.setRedissonClient(client);
        return redissonTemplate;
    }

}

package com.shopping.common.redis;

import com.shopping.common.redis.template.RedisHashTemplate;
import com.shopping.common.redis.template.RedisSetTemplate;
import com.shopping.common.redis.template.RedisZSetTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConditionalOnClass(RedisOperations.class)
public class RedisConfigure {

    @Autowired
    private RedisTemplate redisTemplate;

    //@Bean
    //public RedisConnectionFactory lettuceConnectionFactory(){
    //    RedisSentinelConfiguration sentinelConfig=new RedisSentinelConfiguration().master(
    //            "mymaster").sentinel("127.0.0.1",26379).sentinel("127.0.0.1",26380).
    //            sentinel("127.0.0.1",26381);
    //    sentinelConfig.setPassword(RedisPassword.of("hanhanhan"));
    //    return new LettuceConnectionFactory(sentinelConfig);
    //}

    @Bean
    @ConditionalOnMissingBean(name = "redisHashTemplate")
    public RedisHashTemplate redisHashTemplate(){
        return new RedisHashTemplate(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisSetTemplate")
    public RedisSetTemplate redisSetTemplate(){
        return new RedisSetTemplate(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisZSetTemplate")
    public RedisZSetTemplate redisZSetTemplate(){
        return new RedisZSetTemplate(redisTemplate);
    }


}

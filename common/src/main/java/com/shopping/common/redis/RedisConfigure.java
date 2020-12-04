package com.shopping.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
//public class RedisConfigure {
//
//    @Bean
//    public RedisConnectionFactory lettuceConnectionFactory(){
//        RedisSentinelConfiguration sentinelConfig=new RedisSentinelConfiguration().master(
//                "mymaster").sentinel("127.0.0.1",26379).sentinel("127.0.0.1",26380).
//                sentinel("127.0.0.1",26381);
//        sentinelConfig.setPassword(RedisPassword.of("hanhanhan"));
//        return new LettuceConnectionFactory();
//    }
//
//}

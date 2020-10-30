package com.shopping.common.redis.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisHashTemplate {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisHashTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setHash(Object key,Object hashKey, Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);

    }

    public Object getHash(Object key,Object hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey);
    }

}

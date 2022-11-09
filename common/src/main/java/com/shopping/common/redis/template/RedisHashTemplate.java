package com.shopping.common.redis.template;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisHashTemplate<H, HK, HV> {

    private RedisTemplate redisTemplate;

    public RedisHashTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public void put(H key, HK hashKey, HV value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @SuppressWarnings("unchecked")
    public Boolean putIfAbsent(H key, HK hashKey, HV value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    @SuppressWarnings("unchecked")
    public Object get(Object key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @SuppressWarnings("unchecked")
    public Set<HK> getHashKeys(H key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @SuppressWarnings("unchecked")
    public Boolean hasKey(H key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @SuppressWarnings("unchecked")
    public Long remove(H key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public Boolean expire(H key, long timeout) {
        return expire(key, timeout, null);
    }

    @SuppressWarnings("unchecked")
    public Boolean expire(H key, long timeout, TimeUnit timeUnit) {
        if (timeUnit == null) {
            timeUnit = TimeUnit.SECONDS;
        }
        return redisTemplate.expire(key, timeout, timeUnit);
    }

}

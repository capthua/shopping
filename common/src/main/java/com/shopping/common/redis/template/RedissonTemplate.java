package com.shopping.common.redis.template;

import org.redisson.api.RedissonClient;

public class RedissonTemplate {

    private RedissonClient redissonClient;

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 获取锁, 如果锁不可用,则线程休眠直到获取锁
     *
     * @param name
     */
    public void lock(String name) {
        redissonClient.getLock(name).lock();
    }

    public boolean isLock(String name) {
        return redissonClient.getLock(name).isLocked();
    }

    public void unlock(String name) {
        redissonClient.getLock(name).unlock();
    }

}

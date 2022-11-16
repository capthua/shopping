package com.shopping.common.redis.template;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 不能有重复数据
 *
 * @param <K>
 * @param <V>
 */
public class RedisSetTemplate<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    public RedisSetTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long add(K key, V... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public Long remove(K key, V... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    public Long size(K key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 获取key对应的所有成员
     *
     * @param key
     * @return
     */
    public Set<V> getMembers(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取key对应的一个成员
     *
     * @param key
     * @return
     */
    public V getMember(K key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机弹出指定key对应的集合中的一个成员
     *
     * @param key 不能为空
     * @return
     */
    public V pop(K key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 随机弹出key对应的集合中的成员,数量为count
     *
     * @param key 不能为空
     * @return
     */
    public List<V> pop(K key, long count) {
        return redisTemplate.opsForSet().pop(key, count);
    }

    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    public Boolean expire(K key, long timeout) {
        return expire(key, timeout, null);
    }

    public Boolean expire(K key, long timeout, TimeUnit timeUnit) {
        if (timeUnit == null) {
            timeUnit = TimeUnit.SECONDS;
        }
        return redisTemplate.expire(key, timeout, timeUnit);
    }

}

package com.shopping.common.redis.template;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;


/**
 * 不能有重复数据, 如果score相同字符串按字典顺序
 * The lexicographic ordering used is binary, it compares strings as array of bytes.
 * @param <K>
 * @param <V>
 */
public class RedisZSetTemplate<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    public RedisZSetTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean add(K key, V value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<V> range(K key, long start, long end){
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Set<V> rangeByScore(K key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Set<ZSetOperations.TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    Set<ZSetOperations.TypedTuple<V>> rangeWithScores(K key, long start, long end){
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }


    public Long remove(K key, V... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    public Long removeRangeByScore(K key,double min,double max){
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    public Double incrementScore(K key, V value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }


}

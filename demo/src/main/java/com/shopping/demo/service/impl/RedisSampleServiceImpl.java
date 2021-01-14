package com.shopping.demo.service.impl;

import com.shopping.common.redis.customizedclass.Dog;
import com.shopping.common.redis.customizedclass.User;
import com.shopping.common.redis.template.RedisHashTemplate;
import com.shopping.common.redis.template.RedisSetTemplate;
import com.shopping.common.redis.template.RedisZSetTemplate;
import com.shopping.demo.service.RedisSampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RedisSampleServiceImpl implements RedisSampleService {

    private static final Logger logger=LoggerFactory.getLogger(RedisSampleServiceImpl.class);

    @Autowired(required = false)
    private RedisHashTemplate<String,Object,Object> redisHashTemplate;

    @Autowired(required = false)
    private RedisHashTemplate<User,Object,Object> userRedisHashTemplate;

    @Autowired(required = false)
    private RedisSetTemplate<String,Object> redisSetTemplate;

    @Autowired(required = false)
    private RedisZSetTemplate<String,Object> redisZSetTemplate;

    @Override
    public void setSamples() {
        String han="han";
        Integer age=22;
        Map<String,String> hobbies=new HashMap<>();
        hobbies.put("life","money");
        Map<String,String> work=new HashMap<>();
        work.put("companyName","因联");
        work.put("level","senior");
        redisSetTemplate.add("han",age,hobbies);
        redisSetTemplate.expire("han",10,null);
        redisSetTemplate.add("shao",hobbies,work);
        //redisSetTemplate.expire("shao",10,null);
        Set<Object> hanValues= redisSetTemplate.getMembers("han");
        Set<Object> shaoValues=  redisSetTemplate.getMembers("shao");
        logger.info("hehe");
    }

    @Override
    public void hashSamples() {
        String h1="h1";
        String h2="h2";
        String h1_k1="h1_k1";
        String h1_k2="h1_k2";
        String h1_v1="h1_v1";
        String h1_v2="h1_v2";
        String h2_k1="h2_k1";
        String h2_v1="h2_v1";
//        redisHashTemplate.put(h1,h1_k1,h1_v1);
//        redisHashTemplate.put(h1,h1_k2,h1_v2);
//        redisHashTemplate.put(h2,h2_k1,h2_v1);
        User user1=new User(22,"han");
        User user1_1=new User(22,"han");
        User user2=new User(18,"wang");
        //不同的对象里如果值相同,序列化后的值是相同的
        redisHashTemplate.put(h1,user1,user2);
        redisHashTemplate.put(h2,h1_k1,user2);
        redisHashTemplate.remove(h1,h1_k1,h1_k2);
//        User user3 = (User) redisHashTemplate.get(h1,h1_k1);
        Set hkeys=redisHashTemplate.getHashKeys(h1);
        User keyUser=null;
        for(Object object:hkeys){
           keyUser = (User) object;
        }
        User user4 = (User) redisHashTemplate.get(h1,h1_k2);
        userRedisHashTemplate.put(user1,"hehe","hehe2");
        userRedisHashTemplate.put(user1_1,"hehe1","hehe2");
        logger.info(redisHashTemplate.get(h1,h1_k1).toString());
    }

    @Override
    public void zSetSamples() {
        User user1=new User(22,"han");
        User user2=new User(23,"han");
        User user3=new User(18,"wang");
        Dog dog1=new Dog("jack",2);
        boolean result8 = redisZSetTemplate.add("user",dog1,4);
        boolean result1 = redisZSetTemplate.add("user",user1,4);
        boolean result7 = redisZSetTemplate.add("user",user2,4);
        boolean result2 = redisZSetTemplate.add("user",user3,4);
        boolean result3 = redisZSetTemplate.add("user","hehe",2);
        boolean result4 = redisZSetTemplate.add("user","jiji",2);
        boolean result6 = redisZSetTemplate.add("user","babi",2);
        boolean result5 = redisZSetTemplate.add("user","bibi",2);
        Set<Object> values = redisZSetTemplate.range("user",0,20);
        Long removedCount= redisZSetTemplate.removeRangeByScore("user",0,30);
        logger.info("hehe");
    }

}

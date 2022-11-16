package com.shopping.demo24.dubbo;

import com.shopping.demo24.Demo24Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo24Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * redis hash
     *
     * @throws Exception
     */
    @Test
    public void hashTest() {
        redisTemplate.opsForHash().keys("hehe");
    }

}

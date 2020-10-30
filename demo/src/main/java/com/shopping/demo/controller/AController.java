package com.shopping.demo.controller;

import com.shopping.common.redis.template.RedisHashTemplate;
import com.shopping.common.spring.ApplicationContextUtils;
import io.prs.mybatisx.autoconfigure.MybatisxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("a")
public class AController {

    @Autowired
    MybatisxTemplate template;

    @Autowired
    ApplicationContextUtils applicationContextUtils;

    @Autowired
    RedisHashTemplate redisHashTemplate;

    @GetMapping("a")
    public String a(){
        redisHashTemplate.setHash("han","work","company");
        redisHashTemplate.setHash("han","work","salary");
        redisHashTemplate.setHash("han","study","school");
        redisHashTemplate.getHash("han","study");
        return template.getProperties().toString();
    }

}

package com.shopping.user.controller;

import com.shopping.user.model.User;
import com.shopping.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
//@Slf4j //自动生成log变量
@RestController
@RequestMapping("users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("testUser")
    String testUser(String name) {
        logger.info("testUser参数:{}", name);
        String hehe = userService.testUser(name);
        return "hehe";
    }


    @GetMapping("/{name}")
    String testPathVariableWithDot(@PathVariable("name") String name){
        logger.info(name);
        return "hehe";
    }
}


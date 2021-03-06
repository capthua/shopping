package com.shopping.user.controller;

import com.shopping.user.events.UserChangeSender;
import com.shopping.user.events.UserChange;
//import com.shopping.user.events.queuesend.Sender;
import com.shopping.user.model.User;
import com.shopping.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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

    @Autowired
    UserChangeSender userChangeSender;

//    @Autowired
//    Sender sender;

    @GetMapping("testUser/{name}")
    public User testUser(@PathVariable("name") String name) {
        logger.info("testUser参数:{}", name);
        User user = userService.testUser(name);
        UserChange userChange=new UserChange();
        userChange.setId(new Random().nextInt());
        userChange.setName(new Random().nextInt()+"heheh");
        userChangeSender.pushMsg(userChange);
        return user;
    }


    @GetMapping("/testPath/{name}")
    public String testPathVariableWithDot(@PathVariable("name") String name){
        logger.info(name);
        return "hehe";
    }
}


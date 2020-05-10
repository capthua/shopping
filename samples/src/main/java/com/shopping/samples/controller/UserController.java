package com.shopping.samples.controller;

//import com.shopping.user.events.queuesend.Sender;
import com.shopping.samples.model.User;
import com.shopping.samples.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("testUser/{name}")
    public User testUser(@PathVariable("name") String name) {
        logger.info("testUser参数:{}", name);
        User user=new User();
        user.setUsername(name);
        userService.addUser(user);
        return user;
    }

}


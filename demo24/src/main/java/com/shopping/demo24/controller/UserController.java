package com.shopping.demo24.controller;

import com.shopping.demo24.model.User;
import com.shopping.demo24.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("list")
    public List<User> listUser() {
        return userService.listUsers();
    }

    @GetMapping("remove")
    public Integer removeUser(Long userId) {
        return userService.removeUser(userId);
    }

}


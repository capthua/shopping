package com.shopping.user.controller;

import com.shopping.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("testUser")
    String testUser(String name){
        return userService.testUser(name);
    }
}


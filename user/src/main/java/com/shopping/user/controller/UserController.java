package com.shopping.user.controller;

import com.shopping.common.response.ObjectResponse;
import com.shopping.user.api.UserService;
import com.shopping.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ObjectResponse getById(@PathVariable("id") Long id) {
        UserVO user = userService.getUserById(id);
        ObjectResponse<UserVO> response = new ObjectResponse<>();
        response.setData(user);
        return response;
    }

    @PostMapping
    public ObjectResponse save(@RequestBody UserVO userVO) {
        userService.save(userVO);
        return new ObjectResponse();
    }


}


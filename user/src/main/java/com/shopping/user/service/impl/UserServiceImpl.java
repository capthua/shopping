package com.shopping.user.service.impl;

import com.shopping.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String testUser(String name) {
        return name;
    }
}


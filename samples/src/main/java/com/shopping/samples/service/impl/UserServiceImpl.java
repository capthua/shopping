package com.shopping.samples.service.impl;

import com.shopping.samples.dao.UserDao;
import com.shopping.samples.model.User;
import com.shopping.samples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User testUser(String name) {
        User user = userDao.getUserById(1);
        return user;
    }
}


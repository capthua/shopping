package com.shopping.user.service.impl;

import com.shopping.user.dao.UserDao;
import com.shopping.user.model.User;
import com.shopping.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 如果添加@DubboService注解，可以不用加@Service，bean也会被实例化
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
    public User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        return user;
    }
}


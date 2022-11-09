package com.shopping.demo24.service.impl;

import com.shopping.demo24.dao.UserMapper;
import com.shopping.demo24.model.User;
import com.shopping.demo24.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        int result = userMapper.insertUseGeneratedKeys(user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.select(new User());
    }

    @Override
    public Integer removeUser(Long id) {
        Integer result;
        if(id!=null){
            result=userMapper.deleteByPrimaryKey(id);
        } else {
            result=userMapper.delete(new User());
        }
        return result;
    }
}

package com.shopping.samples.dao;

import com.shopping.samples.model.User;

//@Repository("userDao") //加这个生成的是MapperProxy中的h 是JdkDynamicAopProxy
public interface UserDao {

    User getUserById(Integer id);

    int saveUser(User user);

    int updateUser(User user);
}
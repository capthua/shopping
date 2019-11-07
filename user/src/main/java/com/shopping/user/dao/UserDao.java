package com.shopping.user.dao;

import com.shopping.user.model.User;
import org.springframework.stereotype.Repository;

//@Repository("userDao") //加这个生成的是MapperProxy中的h 是JdkDynamicAopProxy
public interface UserDao {

    User getUserById(Integer id);

    int saveUser(User user);

    int updateUser(User user);
}
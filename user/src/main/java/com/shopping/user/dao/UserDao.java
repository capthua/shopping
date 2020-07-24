package com.shopping.user.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.user.model.User;

//@Repository("userDao") //加这个生成的是MapperProxy中的h 是JdkDynamicAopProxy
public interface UserDao extends CommonMapper<User> {

    User getUserById(Integer id);

    int saveUser(User user);

    int updateUser(User user);
}
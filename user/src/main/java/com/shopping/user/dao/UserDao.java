package com.shopping.user.dao;

import com.shopping.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {

    User getUserById(Integer id);

    int saveUser(User user);

    int updateUser(User user);
}
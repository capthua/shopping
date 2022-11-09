package com.shopping.demo24.service;

import com.shopping.demo24.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User saveUser(User user);

    List<User> listUsers();

    Integer removeUser(Long id);
}

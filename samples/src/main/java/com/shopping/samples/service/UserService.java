package com.shopping.samples.service;

import com.shopping.samples.model.User;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
public interface UserService {

    User testUser(String name);

    User addUser(User user);
}


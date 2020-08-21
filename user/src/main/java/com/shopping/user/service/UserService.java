package com.shopping.user.service;

import com.shopping.user.model.User;

/**
 * describe: 用户service
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
public interface UserService {

    /**
     * 通过id获取user
     * @param id 用户id
     * @return 用户
     */
    User getUserById(Integer id);
}


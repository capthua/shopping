package com.shopping.user.api;


import com.shopping.user.vo.UserVO;

/**
 * 用户service
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
public interface UserService {

    /**
     * 通过id获取user
     *
     * @param id 用户id
     * @return 用户
     */
    UserVO getUserById(Long id);

    /**
     * 创建用户
     *
     * @param userVO
     */
    void save(UserVO userVO);

}


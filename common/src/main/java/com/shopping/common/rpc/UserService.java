package com.shopping.common.rpc;

import java.util.Map;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
public interface UserService {
    Map getUserById(Integer id);
    Map getUserByName(String name);
}


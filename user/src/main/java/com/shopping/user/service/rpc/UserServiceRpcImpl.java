package com.shopping.user.service.rpc;

import com.alibaba.fastjson.JSONObject;
import com.shopping.user.model.User;
import com.shopping.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Capt.Hua
 */
@DubboService(version = "1.0.0",loadbalance = "roundrobin")
public class UserServiceRpcImpl implements com.shopping.common.rpc.UserService {

    @Autowired
    UserService userService;

    @Override
    public Map getUserById(Integer id) {
        User user = userService.getUserById(id);
        return (JSONObject)JSONObject.toJSON(user);
    }
}

package com.shopping.user.service.rpc;

import com.alibaba.fastjson.JSONObject;
import com.shopping.user.model.User;
import com.shopping.user.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Capt.Hua
 */
@Service(version = "1.1",loadbalance = "roundrobin",timeout = 300, retries = 2,actives = 0, executes = 200)
public class UserServiceRpcImpl implements com.shopping.common.rpc.UserService {

    @Autowired
    UserService userService;

    @Override
    public Map getUserById(Integer id) {
        User user = userService.getUserById(id);
        return (JSONObject)JSONObject.toJSON(user);
    }

    @Override
    public Map getUserByName(String name) {
        User user=userService.getUserByName(name);
        return (JSONObject)JSONObject.toJSON(user);
    }
}

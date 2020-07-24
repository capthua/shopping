package com.shopping.user.service.rpc;

import com.alibaba.fastjson.JSONObject;
import com.shopping.common.rpc.UserService;
import com.shopping.user.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@DubboService(version = "1.0.0",loadbalance = "roundrobin")
public class UserServiceRpcImpl implements UserService {

    @Autowired
    com.shopping.user.service.UserService userService;

    @Override
    public Map getUserById(String name) {
        User user = userService.getUserById(name);
        Map heeh=new HashMap();
        heeh.put("han","shaohua");
        return heeh;
    }
}

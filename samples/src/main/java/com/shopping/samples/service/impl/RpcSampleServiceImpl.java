package com.shopping.samples.service.impl;

import com.shopping.common.rpc.UserService;
import com.shopping.samples.service.RpcSampleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author capthua
 */
@Service
public class RpcSampleServiceImpl implements RpcSampleService {

    @Reference(version = "1.0.0", loadbalance = "roundrobin", timeout = 20000, check = false)
    private UserService userService;

    @Override
    public Map getUserById(Integer id) {
        Map user = userService.getUserById(id);
        return user;
    }
}

package com.shopping.user.service.rpc;

import com.shooping.api.dto.AccountDTO;
import com.shooping.api.service.user.UserRpcService;
import com.shopping.common.response.ObjectResponse;
import com.shopping.user.api.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(version = "0.24",timeout = 20000,loadbalance = "roundrobin",retries = 0, actives = 2,executes = 1)
public class UserRpcServiceImpl implements UserRpcService {

    @Autowired
    AccountService accountService;

    @Override
    public ObjectResponse getUserById(Long id) {
        return null;
    }

    @Override
    public ObjectResponse getUserByName(String name) {
        return null;
    }

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        accountService.decreaseAccount(accountDTO.getUserId(),accountDTO.getAmount());
        return new ObjectResponse();
    }
}

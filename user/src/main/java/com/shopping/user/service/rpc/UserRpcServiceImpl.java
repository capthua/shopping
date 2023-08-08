package com.shopping.user.service.rpc;

import com.shooping.api.dto.AccountDTO;
import com.shooping.api.service.user.UserRpcService;
import com.shopping.common.response.ObjectResponse;
import com.shopping.user.api.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DubboService(version = "0.24", timeout = 60000, loadbalance = "roundrobin", retries = 0, actives = 2, executes = 1)
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
    @Transactional
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        accountService.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount());
        return new ObjectResponse();
    }
}

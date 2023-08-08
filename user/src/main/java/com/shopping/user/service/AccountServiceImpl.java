package com.shopping.user.service;

import com.shopping.user.dao.mapper.AccountMapper;
import com.shopping.user.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public void decreaseAccount(Long userId, BigDecimal amount) {
        accountMapper.decreaseAccount(userId, amount.doubleValue());
        if(amount.intValue()==2000){
            throw new RuntimeException("user服务异常");
        }
    }
}

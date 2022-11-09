package com.shopping.user.service;

import com.shopping.user.dao.mapper.AccountMapper;
import com.shopping.user.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decreaseAccount(Long userId, BigDecimal amount) {
        accountMapper.decreaseAccount(userId, amount.doubleValue());
    }
}

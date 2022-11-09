package com.shopping.user.api;

import java.math.BigDecimal;

public interface AccountService {
    void decreaseAccount(Long userId, BigDecimal amount);
}

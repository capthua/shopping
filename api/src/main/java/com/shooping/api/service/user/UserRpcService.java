package com.shooping.api.service.user;

import com.shooping.api.dto.AccountDTO;
import com.shopping.common.response.ObjectResponse;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
public interface UserRpcService {
    ObjectResponse getUserById(Long id);
    ObjectResponse getUserByName(String name);

    /**
     * 从账户扣钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}


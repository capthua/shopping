package com.shopping.user.dao.mapper;

import com.shopping.common.db.CommonMapper;
import com.shopping.user.dao.dataobject.TAccount;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends CommonMapper<TAccount> {

    int decreaseAccount(@Param("userId") Long userId, @Param("amount") Double amount);

}

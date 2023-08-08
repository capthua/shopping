package com.shopping.order.dao.mapper;

import com.shopping.common.db.CommonMapper;
import com.shopping.order.dao.dataobject.OrderDO;

public interface OrderMapper extends CommonMapper<OrderDO> {

    int saveOrder(OrderDO orderDO);

}

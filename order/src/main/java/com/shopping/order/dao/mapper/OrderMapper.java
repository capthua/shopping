package com.shopping.order.dao.mapper;

import com.shopping.common.db.CommonMapper;
import com.shopping.order.dao.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper extends CommonMapper<OrderDO> {
}

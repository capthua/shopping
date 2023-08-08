package com.shopping.demo24.dao.mapper;

import com.shopping.common.db.CommonMapper;
import com.shopping.demo24.dao.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapperStd extends CommonMapper<OrderDO> {

    @Select("select * from t_order where id=#{id}")
    OrderDO getById(@Param("id") Long id);

}

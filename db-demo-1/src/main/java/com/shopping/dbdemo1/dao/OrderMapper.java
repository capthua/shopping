package com.shopping.dbdemo1.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.dbdemo1.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper extends CommonMapper<Order> {

    @Select("select * from t_order where id=#{id}")
    Order getById(@Param("id") String id);

}

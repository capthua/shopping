package com.shopping.order.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.order.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends CommonMapper<Order> {

    @Select("select * from tb_order where id=#{id}")
    Order getById(@Param("id")String id);

}

package com.shopping.demo24.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.demo24.model.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper extends CommonMapper<OrderItem> {

    @Select("select oi.* from t_order_item oi join t_order o on oi.order_id=o.id where o.id=#{orderId}")
    List<OrderItem> listItems(@Param("orderId") Long orderId);

    @Select("select * from t_order o join t_order_item oi on oi.order_id=o.order_id where o.order_id in (${orderIds})")
    List<Map> getOrderDetails(@Param("orderIds") String orderIds);

    @Select("select * from t_order where order_id in (${orderIds})")
    List<Map> getOrder(@Param("orderIds") String orderIds);

}

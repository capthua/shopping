package com.shopping.demo24.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.demo24.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderMapper extends CommonMapper<Order> {

    @Select("select * from t_order where id=#{id}")
    Order getById(@Param("id") Long id);

    @Update("update t_order set user_id=#{newUserId} where user_id=#{srcUserId}")
    int updateUserIdByUserId(@Param("newUserId") String newUserId, @Param("srcUserId") String srcUserId);

    @Update("update t_order set user_id=#{newUserId} where id in (${ids})")
    int updateUserIdByIds(@Param("newUserId") String newUserId, @Param("ids") String ids);

}

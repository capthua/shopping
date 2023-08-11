package com.shopping.order.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order_item")
public class OrderItemDO {
    @TableId
    private Long id;
    private Long orderId;
    private Long goodsId;
    private Integer quantity;
}

package com.shopping.order.dao.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "t_order_item")
public class OrderItemDO {
    private Long id;
    private Long orderId;
    private Long goodsId;
    private Integer quantity;
}

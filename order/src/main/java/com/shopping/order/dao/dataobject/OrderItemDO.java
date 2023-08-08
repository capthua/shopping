package com.shopping.order.dao.dataobject;

//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "t_order_item")
//@TableName("t_order_item")
public class OrderItemDO {
    @Id
    private Long id;
    private Long orderId;
    private Long goodsId;
    private Integer quantity;
}

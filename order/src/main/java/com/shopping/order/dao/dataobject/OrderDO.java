package com.shopping.order.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.math.BigDecimal;

@Data
@TableName("t_order")
public class OrderDO {

    @TableId
    private Long id;
    private BigDecimal totalCost;
    private Long createTime;
    private Long modifyTime;
    private Byte state;
    private Long userId;
}

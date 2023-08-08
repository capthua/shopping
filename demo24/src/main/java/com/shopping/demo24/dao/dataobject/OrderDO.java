package com.shopping.demo24.dao.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "t_order")
public class OrderDO {

    @Id
    private Long id;
    private BigDecimal totalCost;
    private Long createTime;
    private Long modifyTime;
    private Byte state;
    private Long userId;
}

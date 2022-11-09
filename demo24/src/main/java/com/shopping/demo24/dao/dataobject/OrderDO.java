package com.shopping.demo24.dao.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "sp_order")
public class OrderDO {
    @Id
    private Long id;
    private Date createTime;
    private Date modifyTime;
    private BigDecimal totalCost;
    private Long paymentId;
    private Long deliveryId;
    private Byte state;
    private Long userId;
}

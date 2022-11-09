package com.shopping.goods.dao.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "t_goods")
public class GoodsDO {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

}

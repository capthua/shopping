package com.shopping.goods.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_goods")
public class GoodsDO {

    @TableId
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

}

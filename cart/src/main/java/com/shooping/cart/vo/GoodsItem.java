package com.shooping.cart.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsItem implements Serializable {
    private Long goodsId;
    private Integer quantity;

}

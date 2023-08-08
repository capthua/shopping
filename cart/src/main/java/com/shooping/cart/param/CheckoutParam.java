package com.shooping.cart.param;

import com.shooping.cart.vo.GoodsItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CheckoutParam implements Serializable {

    List<GoodsItem> goodsItems;
    private Long userId;
    private BigDecimal amount;

    boolean rollback;
}

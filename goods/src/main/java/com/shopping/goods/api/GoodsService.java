package com.shopping.goods.api;

public interface GoodsService {

    void decreaseStock(Long goodsId, Integer quantity);

}

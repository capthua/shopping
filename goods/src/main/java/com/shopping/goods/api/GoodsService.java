package com.shopping.goods.api;

import com.shopping.goods.dao.dataobject.GoodsDO;

public interface GoodsService {

    void decreaseStock(Long goodsId, Integer quantity);

    GoodsDO getGoodsById(Long goodsId);

}

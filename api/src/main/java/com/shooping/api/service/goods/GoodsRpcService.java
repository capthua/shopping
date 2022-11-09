package com.shooping.api.service.goods;


import com.shooping.api.dto.CommodityDTO;
import com.shopping.common.response.ObjectResponse;

import java.util.List;

public interface GoodsRpcService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStock(List<CommodityDTO> commodities);
}

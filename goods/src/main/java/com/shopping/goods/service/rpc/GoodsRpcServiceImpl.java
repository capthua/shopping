package com.shopping.goods.service.rpc;

import com.shooping.api.dto.CommodityDTO;
import com.shooping.api.service.goods.GoodsRpcService;
import com.shopping.common.response.ObjectResponse;
import com.shopping.goods.api.GoodsService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@DubboService(version = "0.24", timeout = 60000, loadbalance = "roundrobin", retries = 0, actives = 2)
public class GoodsRpcServiceImpl implements GoodsRpcService {

    @Autowired
    GoodsService goodsService;

    @Override
    @Transactional
    public ObjectResponse decreaseStock(List<CommodityDTO> commodities) {
        log.info("全局事务id ：" + RootContext.getXID());
        commodities.forEach(commodityDTO -> {
            goodsService.decreaseStock(commodityDTO.getId(), commodityDTO.getCount());
        });
        return new ObjectResponse();
    }
}

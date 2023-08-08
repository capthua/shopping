package com.shopping.order.service.rpc;

import com.shooping.api.service.order.OrderAdsService;
import com.shooping.api.service.order.OrderParam;
import com.shopping.common.response.ObjectResponse;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "0.24", timeout = 60000, loadbalance = "roundrobin", retries = 0, actives = 2)
public class OrderAdsRpcServiceImpl implements OrderAdsService {

    @Override
    public ObjectResponse getOrderAds(OrderParam orderParam) {
        ObjectResponse result = new ObjectResponse();
        result.setMsg("获取订单相关广告");
        return result;
    }
}

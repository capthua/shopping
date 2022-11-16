package com.shooping.api.service.order;


import com.shooping.api.dto.OrderDTO;
import com.shopping.common.response.ObjectResponse;

public interface OrderRpcService {
    ObjectResponse getOrder(OrderParam orderParam);

    ObjectResponse validateOrder(OrderParam orderParam);

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);

}

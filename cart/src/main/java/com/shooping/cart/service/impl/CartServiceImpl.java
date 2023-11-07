package com.shooping.cart.service.impl;

import com.shooping.api.dto.CommodityDTO;
import com.shooping.api.dto.OrderDTO;
import com.shooping.api.dto.OrderItemDTO;
import com.shooping.api.service.goods.GoodsRpcService;
import com.shooping.api.service.order.OrderRpcService;
import com.shooping.cart.param.CheckoutParam;
import com.shooping.cart.service.CartService;
import com.shopping.common.business.OrderUtils;
import com.shopping.common.exception.DefaultException;
import com.shopping.common.response.ObjectResponse;
import com.shopping.common.response.RspStatusEnum;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @DubboReference(timeout = 60000, version = "0.24", async = false, check = false)
    private GoodsRpcService goodsService;

    @DubboReference(timeout = 60000, version = "0.24", async = false, check = false)
    private OrderRpcService orderService;


    @GlobalTransactional(timeoutMills = 300000, name = "shopping-checkout")
    @Override
    public ObjectResponse checkout(CheckoutParam checkoutParam) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();

        List<OrderItemDTO> orderItems = new ArrayList<>();

        //1.减库存
        List<CommodityDTO> commodities = new ArrayList<>();
        checkoutParam.getGoodsItems().forEach(item -> {
            CommodityDTO comodity = new CommodityDTO();
            comodity.setId(item.getGoodsId());
            comodity.setCount(item.getQuantity());
            commodities.add(comodity);

            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setGoodsId(item.getGoodsId());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
        });
        ObjectResponse stockResponse = goodsService.decreaseStock(commodities);

        //2.创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(OrderUtils.genOrderNo());
        orderDTO.setUserId(checkoutParam.getUserId());
        orderDTO.setItems(orderItems);
        orderDTO.setAmount(checkoutParam.getAmount());
        ObjectResponse orderResponse = orderService.createOrder(orderDTO);

        //        打开注释测试事务发生异常后，全局回滚功能
        if (checkoutParam.isRollback()) {
            log.error("测试抛异常后，分布式事务回滚！");
            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
        }

        if (stockResponse.getStatus() != RspStatusEnum.SUCCESS.getStatus() ||
                orderResponse.getStatus() != RspStatusEnum.SUCCESS.getStatus()) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getStatus());
        objectResponse.setMsg(RspStatusEnum.SUCCESS.getMsg());
        return objectResponse;
    }

    @Override
    public ObjectResponse getGoodsById(Long id) {
        ObjectResponse or= new ObjectResponse();
        or.setData(goodsService.getGoodsById(id).getData());
        return or;
    }
}

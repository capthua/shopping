package com.shopping.order.service.rpc;

import com.shooping.api.dto.AccountDTO;
import com.shooping.api.dto.OrderDTO;
import com.shooping.api.dto.OrderItemDTO;
import com.shooping.api.service.order.OrderParam;
import com.shooping.api.service.order.OrderRpcService;
import com.shooping.api.service.user.UserRpcService;
import com.shopping.common.id.SnowflakeShardingKeyGenerator;
import com.shopping.common.response.ObjectResponse;
import com.shopping.order.api.OrderService;
import com.shopping.order.api.model.OrderItemModel;
import com.shopping.order.api.model.OrderModel;
import com.shopping.order.dao.dataobject.OrderDO;
import com.shopping.order.dao.dataobject.OrderItemDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DubboService(version = "0.24", timeout = 60000, loadbalance = "roundrobin", retries = 0, actives = 2, executes = 1)
public class OrderRpcServiceImpl implements OrderRpcService {

    @DubboReference(timeout = 60000, version = "0.24", async = false, check = false)
    private UserRpcService userRpcService;

    @Autowired
    OrderService orderService;

    @Override
    public ObjectResponse getOrder(OrderParam orderParam) {
        log.info("获取订单,参数:{}", orderParam);
        ObjectResponse result = new ObjectResponse();
        result.setMsg("获取订单");
        log.info("dubbo info");
        log.error("dubbo error");
//        if(orderParam.getOrderId()==12){
//            int a=3/0;
//        }
        return result;
    }

    @Override
    public ObjectResponse validateOrder(OrderParam orderParam) {
        ObjectResponse result = new ObjectResponse();
        result.setMsg("校验订单");
        return result;
    }

    @Override
    @Transactional
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();

//        //生成订单
//        orderService.saveOrder(getOrderModel(orderDTO));
//        //扣减用户账户
//        AccountDTO accountDTO = new AccountDTO();
//        accountDTO.setUserId(orderDTO.getUserId());
//        accountDTO.setAmount(orderDTO.getAmount());
//        ObjectResponse accountResponse = userRpcService.decreaseAccount(accountDTO);

        orderService.createOrder(orderDTO);

        return response;
    }

    public static OrderModel getOrderModel(OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(orderDTO.getUserId());
        orderModel.setState((byte) 0);
        orderModel.setTotalCost(orderDTO.getAmount());
        List<OrderItemDTO> orderItemDTOs = orderDTO.getItems();
        List<OrderItemModel> orderItemModels = new ArrayList<>();
        orderItemDTOs.forEach(orderItemDTO -> {
            OrderItemModel orderItemModel = new OrderItemModel();
            BeanUtils.copyProperties(orderItemDTO, orderItemModel);
            orderItemModels.add(orderItemModel);
        });
        orderModel.setOrderItems(orderItemModels);
        return orderModel;
    }
}

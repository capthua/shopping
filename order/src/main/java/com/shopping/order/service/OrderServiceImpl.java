package com.shopping.order.service;

import com.shooping.api.dto.AccountDTO;
import com.shooping.api.dto.OrderDTO;
import com.shooping.api.service.user.UserRpcService;
import com.shopping.common.id.SnowflakeShardingKeyGenerator;
import com.shopping.common.response.ObjectResponse;
import com.shopping.order.api.model.OrderModel;
import com.shopping.order.dao.dataobject.OrderItemDO;
import com.shopping.order.dao.mapper.OrderItemMapper;
import com.shopping.order.dao.mapper.OrderMapper;
//import com.shopping.order.dao.mapper.OrderMapperMP;
import com.shopping.order.dao.dataobject.OrderDO;
import com.shopping.order.api.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.shopping.order.service.rpc.OrderRpcServiceImpl.getOrderModel;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @DubboReference(timeout = 60000, version = "0.24", async = false, check = false)
    private UserRpcService userRpcService;

    @Autowired
    SnowflakeShardingKeyGenerator idGenerator;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public int sendOrderMsg() {
        //Order order=new Order();
        //order.setId(23L);
        //order.setName("hehe");
        //sender.pushMsg(order);
        return 0;
    }

    @Transactional
    @Override
    public int saveVastOrders(int num) {
        List<OrderDO> orders = new ArrayList<>();
        Date currentTime = new Date();
        for (int i = 0; i < num; i++) {
            OrderDO order = new OrderDO();
            order.setId(Long.parseLong(i + ""));
            order.setCreateTime(currentTime.getTime());
            order.setModifyTime(currentTime.getTime());
            order.setTotalCost(BigDecimal.valueOf(55.2));
            order.setState((byte) 1);
            order.setUserId(Long.parseLong(i + ""));
            orders.add(order);
        }
        logger.info("开始插入");
        long startTime = System.currentTimeMillis();
        int result = 0;
//                orderMapper.insertList(orders);
        long useTime = System.currentTimeMillis() - startTime;
        logger.info("插入结束,用时:{}秒", useTime / 1000);
        return result;
    }

    @Override
    public OrderModel getOrderById(Long id) {
//        OrderDO orderDO = orderMapper.selectByPrimaryKey(id);
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(null, orderModel);
        return orderModel;
    }

    @Override
    public void setState(Long id, Byte status) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(id);
        orderDO.setState(status);
        orderDO.setModifyTime(System.currentTimeMillis());
//        orderMapper.updateByPrimaryKeySelective(orderDO);
    }

    @Transactional
    @Override
    public void saveOrder(OrderModel order) {
        Long currentTime = System.currentTimeMillis();
        order.setCreateTime(currentTime);
        order.setCreateTime(currentTime);
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(order, orderDO);
        orderDO.setId(idGenerator.generateKey());
        orderMapper.saveOrder(orderDO);

        List<OrderItemDO> orderItemDOs = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            OrderItemDO orderItemDO = new OrderItemDO();
            BeanUtils.copyProperties(orderItem, orderItemDO);
            orderItemDO.setOrderId(orderDO.getId());
            orderItemDO.setId(idGenerator.generateKey());
            orderItemDOs.add(orderItemDO);
            orderItemMapper.insert(orderItemDO);
        });
    }


    @Override
    @Transactional
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        //生成订单
        saveOrder(getOrderModel(orderDTO));
        //扣减用户账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getAmount());
        ObjectResponse accountResponse = userRpcService.decreaseAccount(accountDTO);
        return response;
    }
}

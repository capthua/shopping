package com.shopping.demo24.service.impl;


import com.shopping.demo24.api.CacheDemoService;
import com.shopping.demo24.api.model.OrderModel;
import com.shopping.demo24.dao.dataobject.OrderDO;
import com.shopping.demo24.dao.mapper.OrderMapperStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;


@Service
public class CacheDemoServiceImpl implements CacheDemoService {

    @Autowired
    CacheManager cacheManager;

    private static final Logger logger = LoggerFactory.getLogger(CacheDemoServiceImpl.class);

    @Autowired
    private OrderMapperStd orderMapper;



    @Cacheable(value = "order",key = "#id")
    @Override
    public OrderModel getOrderById(Long id) {
        OrderDO orderDO=orderMapper.getById(id);
        OrderModel orderModel=new OrderModel();
        BeanUtils.copyProperties(orderDO,orderModel);
        return orderModel;
    }

    @Cacheable(value = "orderB",key = "#id")
    @Override
    public OrderModel getOrderBById(Long id) {
        OrderDO orderDO=orderMapper.getById(id);
        OrderModel orderModel=new OrderModel();
        BeanUtils.copyProperties(orderDO,orderModel);
        return orderModel;
    }

    /**
     * 一般都是先操作数据库，再删除缓存
     * @param id
     * @param status
     * @return
     */
    @Override
//    @CacheEvict(value = "order",key = "#id",beforeInvocation = false)
    @CachePut(value = "order",key = "#id")
    @Transactional
    public OrderModel setState(Long id, Byte status) {
        OrderDO orderDO=new OrderDO();
        orderDO.setId(id);
        orderDO.setState(status);
        orderDO.setModifyTime(new Date());
        orderMapper.updateByPrimaryKeySelective(orderDO);
        return getOrderById(id);
    }


}

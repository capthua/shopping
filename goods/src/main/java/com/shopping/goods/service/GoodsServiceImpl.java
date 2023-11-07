package com.shopping.goods.service;

import com.shopping.goods.api.GoodsService;
import com.shopping.goods.dao.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Transactional
    public void decreaseStock(Long goodsId, Integer quantity) {
        goodsMapper.decreaseStock(goodsId, quantity);
//        goodsMapper.deleteByPrimaryKey(2);
//        goodsMapper.removeById(2L);
    }
}

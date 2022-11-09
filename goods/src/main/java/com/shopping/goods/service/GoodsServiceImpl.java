package com.shopping.goods.service;

import com.shopping.goods.api.GoodsService;
import com.shopping.goods.dao.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void decreaseStock(Long goodsId, Integer quantity) {
        goodsMapper.decreaseStock(goodsId, quantity);
    }
}
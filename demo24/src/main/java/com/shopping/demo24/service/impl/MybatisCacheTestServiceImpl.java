package com.shopping.demo24.service.impl;

import com.shopping.demo24.dao.ADao;
import com.shopping.demo24.dao.ADetailDao;
import com.shopping.demo24.model.A;
import com.shopping.demo24.service.MybatisCacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MybatisCacheTestServiceImpl implements MybatisCacheTestService {

    @Autowired
    ADao aDao;

    @Autowired
    ADetailDao aDetailDao;

    @Transactional
    @Override
    public void secondLevelCacheTest() {
        A a = aDao.getById(1);
        String anme = "hehe";
        aDao.updateNameById(anme, 1);
        System.out.println("hehe");
    }

}

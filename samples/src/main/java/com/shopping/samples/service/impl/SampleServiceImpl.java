package com.shopping.samples.service.impl;

import com.shopping.samples.dao.SampleBDao;
import com.shopping.samples.dao.SampleDao;
import com.shopping.samples.model.Sample;
import com.shopping.samples.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.ExecutionException;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    private SampleBDao sampleBDao;

//    编码式事务
    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    TxPropagationSampleService txPropagationSampleService;

    @Override
    @Transactional
    public int saveSample(Sample sample) {

        Sample temp= sampleDao.selectByPrimaryKey(1);
        int result=sampleDao.insertUseGeneratedKeys(sample);
        if(sample.getDescription().equals("1")){
            int i=3/0;
        }
        sample.setDescription("1");

        //never
//        txPropagationSampleService.saveSample3(sample);

        //not supported
//        txPropagationSampleService.saveSample4(sample);
//requires_new
        txPropagationSampleService.saveSample5(sample);

        //嵌套事务
//        try {
//            txPropagationSampleService.saveSample2(sample);
//        } catch (Exception e){
//            //catch异常后外层事务不回滚
//        }

        return result;


//        //编码式事务
//        transactionTemplate.execute(txStatus->{
//            try {
//                Sample temp= sampleDao.selectByPrimaryKey(1);
//                int result=sampleDao.insertUseGeneratedKeys(sample);
//                if(sample.getDescription().equals("1")){
//                    int i=3/0;
//                }
//            } catch (Exception e){
////                txStatus.setRollbackOnly();
//                throw e;
//            }
//            return null;
//        });
//        return 0;
    }
}

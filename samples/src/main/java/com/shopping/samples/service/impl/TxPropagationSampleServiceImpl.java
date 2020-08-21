package com.shopping.samples.service.impl;

import com.shopping.samples.dao.SampleBDao;
import com.shopping.samples.dao.SampleDao;
import com.shopping.samples.model.Sample;
import com.shopping.samples.service.TxPropagationSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxPropagationSampleServiceImpl implements TxPropagationSampleService {

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    private SampleBDao sampleBDao;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveSample1(Sample sample) {
        String srcName=sample.getName();
        sample.setName("1 mandatory"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void saveSample2(Sample sample) {
        String srcName=sample.getName();
        sample.setName("2 nested"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
        if(sample.getDescription().equals("1")){
            int i=3/0;
        }
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void saveSample3(Sample sample) {
        String srcName=sample.getName();
        sample.setName("3 never"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveSample4(Sample sample) {
        String srcName=sample.getName();
        sample.setName("4 not supported"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSample5(Sample sample) {
        String srcName=sample.getName();
        sample.setName("5 requires new"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveSample6(Sample sample) {
        String srcName=sample.getName();
        sample.setName("6 supports"+sample.getName());
        sampleDao.insertUseGeneratedKeys(sample);
        sample.setName(srcName);
    }
}

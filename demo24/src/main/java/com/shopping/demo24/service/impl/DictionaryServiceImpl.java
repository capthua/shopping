package com.shopping.demo24.service.impl;

import com.shopping.demo24.dao.DictionaryMapper;
import com.shopping.demo24.model.Dictionary;
import com.shopping.demo24.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    //@Autowired
    //ShardingDataSource shardingDataSource;

    @Override
    public Dictionary save(Dictionary dictionary) {
//        dictionary.setId(shardingDataSource.getRuntimeContext().);
//        dictionary.setId((Long) shardingDataSource.getRuntimeContext().getRule().getDefaultShardingKeyGenerator().generateKey());
//        dictionaryMapper.insert(dictionary);
        return dictionary;
    }
}

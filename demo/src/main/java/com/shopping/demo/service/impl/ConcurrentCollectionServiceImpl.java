package com.shopping.demo.service.impl;

import com.shopping.demo.service.ConcurrentCollectionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConcurrentCollectionServiceImpl implements ConcurrentCollectionService {

    private Map<String,String> printerH=new HashMap<>();
    private Map<String,String> printerC=new ConcurrentHashMap<>();
    private Map<String,String> printerHt=new Hashtable<>();

    @Override
    public void testCHashMap(String key,String value) {
        Map<String,String> printer=printerC;
        printer.put(key,value);
        String latestValue=printer.get(key);
        System.out.println(latestValue);
        latestValue=printer.get(key);
        System.out.println(latestValue);
    }
}

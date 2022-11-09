package com.shopping.demo24.controller;

import com.shopping.demo24.model.Dictionary;
import com.shopping.demo24.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("dict")
public class DictController {

    private static final Logger logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    DictionaryService dictionaryService;

    @PostMapping("save")
    public Dictionary saveUser(@RequestBody Dictionary dictionary) {
        return dictionaryService.save(dictionary);
    }


}


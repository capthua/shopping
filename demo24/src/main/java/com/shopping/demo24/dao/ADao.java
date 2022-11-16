package com.shopping.demo24.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.demo24.model.A;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.decorators.LruCache;

public interface ADao extends CommonMapper<A> {

    A getById(@Param("id") Integer id);

    A getById2(@Param("id") Integer id);

    void updateNameById(@Param("name") String name, @Param("id") Integer id);

}

package com.shopping.goods.dao.mapper;

import com.shopping.common.db.CommonMapper;
import com.shopping.goods.dao.dataobject.GoodsDO;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends CommonMapper<GoodsDO> {

    int decreaseStock(@Param("id") Long id, @Param("count") Integer count);

}

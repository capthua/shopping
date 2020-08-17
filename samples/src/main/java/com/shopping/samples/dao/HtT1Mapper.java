package com.shopping.samples.dao;


import com.shopping.common.db.CommonMapper;
import com.shopping.samples.model.HtT1;
import com.shopping.samples.vo.HtT1Vo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HtT1Mapper extends CommonMapper<HtT1> {

    List<HtT1Vo> listDeepChildren(@Param("parentId")Integer parentId,@Param("name")String name);
}

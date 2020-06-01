package com.shopping.samples.dao;

import com.shopping.common.db.CommonMapper;
import com.shopping.samples.model.Sample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository("userDao") //加这个生成的是MapperProxy中的h 是JdkDynamicAopProxy
public interface SampleDao extends CommonMapper<Sample> {

    List<Sample> list(@Param("condition")Sample condition);

    int update(@Param("sample")Sample sample,@Param("condition")Sample condition);
}
package com.shopping.common.db;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TkCommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

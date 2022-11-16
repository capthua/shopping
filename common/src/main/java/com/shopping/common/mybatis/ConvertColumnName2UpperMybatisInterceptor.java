package com.shopping.common.mybatis;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.shopping.common.mybatis.PRSMybatisInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

/**
 * @author CaptHua
 */
@Component
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),})
public class ConvertColumnName2UpperMybatisInterceptor extends PRSMybatisInterceptor {

    private static final Converter<String, String> LU2LC_CONVERTER =
            CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);

    @Override
    public Object before(Invocation invocation) throws Throwable {
        List<Map<String, Object>> result = new ArrayList<>();
        DefaultResultSetHandler defaultResultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(defaultResultSetHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");

        List<ResultMap> resultMaps = mappedStatement.getResultMaps();

        Class<?> resultType = resultMaps.get(0).getType();

        //如果返回的是Map类型的话，如果map的key格式是lower_underscore，则转化哪位lowerCamel
        if (resultType.isAssignableFrom(Map.class)) {
            int resultMapCount = resultMaps.size();
            if (resultMapCount > 0) {
                Statement statement = (Statement) invocation.getArgs()[0];
                ResultSet resultSet = statement.getResultSet();
                if (resultSet != null) {

                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    List<String> columnList = new ArrayList<>();
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        columnList.add(resultSetMetaData.getColumnName(i));
                    }
                    while (resultSet.next()) {
                        Map<String, Object> resultMap = new HashMap<>();
                        for (String originColumnName : columnList) {
                            if (Objects.nonNull(originColumnName)) {
                                String columnName = LU2LC_CONVERTER.convert(originColumnName);
                                if (StringUtils.isAllUpperCase(columnName)) {
                                    resultMap.put(columnName.toLowerCase(), resultSet.getObject(originColumnName));
                                } else {
                                    resultMap.put(columnName, resultSet.getObject(originColumnName));
                                }
                            }
                        }
                        result.add(resultMap);

                    }
                    needBreak = true;
                    return result;
                }
            }
        }
        return null;
    }

}

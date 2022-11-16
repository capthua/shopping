package com.shopping.common.mybatis;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.logging.jdbc.StatementLogger;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author CaptHua
 */
@Component
@Order(1)
@Intercepts({@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),})
public class SqlValidateMybatisInterceptor extends PRSMybatisInterceptor {

    @Override
    protected Object before(Invocation invocation) throws Throwable {
        String sql = "";
        Statement statement = (Statement) invocation.getArgs()[0];
        if (Proxy.isProxyClass(statement.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(statement);
            Object h = metaObject.getValue("h");
            if (h instanceof StatementLogger) {
                RoutingStatementHandler rsh = (RoutingStatementHandler) invocation.getTarget();
                sql = rsh.getBoundSql().getSql();
            } else {
                PreparedStatementLogger psl = (PreparedStatementLogger) h;
                sql = psl.getPreparedStatement().toString();
            }
        } else {
            sql = statement.toString();
        }
        if (containsDelete(sql) && !containsWhere(sql)) {
            throw new SQLException("不能删除整张表,sql:" + sql);
        }
        return null;
    }

    private boolean containsDelete(String sql) {
        return sql.contains("delete") || sql.contains("DELETE");
    }

    private boolean containsWhere(String sql) {
        return sql.contains("where") || sql.contains("WHERE");
    }
}

//package com.shopping.order.common;
//
//import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
//
//import java.time.LocalDateTime;
//
//public class P6SpyLogger implements MessageFormattingStrategy {
//	/**
//     *
//     * @param connectionId 连接id
//     * @param now 当前时间
//     * @param elapsed 耗时
//     * @param category 类型
//     * @param prepared 声明的sql语句
//     * @param sql 有效的sql语句
//     * @param datasource 连接属性
//     * @return
//     */
//    @Override
//    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql,String datasource) {
//        return !"".equals(sql.trim()) ? "[ " + LocalDateTime.now() + " ] --- | took "
//                + elapsed + "ms | " + category + " | connection " + connectionId +"\n " + "datasource " + datasource +"\n "
//                + sql + ";" : "";
//    }
//}
package com.shopping.dbdemo1.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DbContextHolder {
    public static final String WRITE="write";
    public static final String READ="read";

    private static ThreadLocal<String> contextHolder= ThreadLocal.withInitial(() -> WRITE);

    static void setDbOpType(String dbOpType){
        if(StringUtils.isBlank(dbOpType)){
            log.error("opType为空");
            throw new NullPointerException("opType为空");
        }
        log.info("设置dbOpType为：{}",dbOpType);
        contextHolder.set(dbOpType);
    }

    static String getDbOpType(){
        return contextHolder.get();
    }

    static void clearDbOpType() {
        contextHolder.remove();
    }
}

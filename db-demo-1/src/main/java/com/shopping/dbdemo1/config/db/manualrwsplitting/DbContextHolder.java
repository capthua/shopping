package com.shopping.dbdemo1.config.db.manualrwsplitting;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
class DbContextHolder {
    static final String WRITE="write";
    static final String READ="read";

    private static ThreadLocal<String> contextHolder= ThreadLocal.withInitial(() -> WRITE);

    static void setDbOpType(String dbOpType){
        if(StringUtils.isBlank(dbOpType)){
            log.error("opType为空");
            throw new NullPointerException("opType为空");
        }
        if(log.isDebugEnabled()){
            log.debug("设置dbOpType为：{}",dbOpType);
        }
        contextHolder.set(dbOpType);
    }

    static String getDbOpType(){
        return contextHolder.get();
    }

    static void clearDbOpType() {
        contextHolder.remove();
    }
}

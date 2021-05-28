package com.shopping.dbdemo1.config.db.rwseparation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dbOpType=DbContextHolder.getDbOpType();
        if(dbOpType.equals(DbContextHolder.WRITE)){
            if(logger.isDebugEnabled()){
                logger.debug("使用了写库");
            }
        } else {
            if(logger.isDebugEnabled()){
                logger.info("使用了读库");
            }
        }
        return dbOpType;
    }
}

package com.shopping.dbdemo1.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dbOpType=DbContextHolder.getDbOpType();
        if(dbOpType.equals(DbContextHolder.WRITE)){
            logger.info("使用了写库");
        } else {
            logger.info("使用了读库");
        }
        return dbOpType;
    }
}

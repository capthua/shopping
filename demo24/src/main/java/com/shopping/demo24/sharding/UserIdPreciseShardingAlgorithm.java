package com.shopping.demo24.sharding;//package com.shopping.demo.config.db.sharding;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//
//import java.util.Collection;
//
//@Slf4j
//public class UserIdPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
//    @Override
//    public String doSharding(Collection<String> availableTargetNames,
//                             PreciseShardingValue<String> shardingValue) {
//        String targetTableName=
//                shardingValue.getLogicTableName()+"_"+shardingValue.getValue().replace("u","");
//        if(log.isInfoEnabled()){
//            log.info("targetTableName:{}",targetTableName);
//        }
//        return targetTableName;
//    }
//}

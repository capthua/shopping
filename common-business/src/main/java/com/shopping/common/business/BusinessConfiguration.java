package com.shopping.common.business;

import com.shopping.common.id.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfiguration {

    @Value("${workerId:0}")
    private Long workerId;

    @Bean
    public SnowflakeShardingKeyGenerator idGenerator() {
        SnowflakeShardingKeyGenerator idGenerator = new SnowflakeShardingKeyGenerator();
        idGenerator.setWorkerId(workerId);
        return idGenerator;
    }
}

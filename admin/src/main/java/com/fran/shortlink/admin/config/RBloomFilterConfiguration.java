package com.fran.shortlink.admin.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bloom Filter Config
 */
@Configuration
public class RBloomFilterConfiguration {

    /**
     * Bloom filter for user registration
     */
    @Bean
    public RBloomFilter<String> userRegisterCachePenetrationBloomFilter(
        RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(
            "userRegisterCachePenetrationBloomFilter");
        /*
            `expectedInsertions`: The estimated number of elements the Bloom Filter will store.
            `falseProbability`: The acceptable false positive rate.
         */
        cachePenetrationBloomFilter.tryInit(100_000_000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}

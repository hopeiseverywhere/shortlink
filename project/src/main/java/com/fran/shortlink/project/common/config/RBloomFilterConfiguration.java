package com.fran.shortlink.project.common.config;

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
     * Bloom filter for short uri creation
     */
    @Bean
    public RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter(
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

package com.fran.shortlink.admin.config;

import com.fran.shortlink.admin.common.biz.user.UserTransmitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * User Configuration Auto-Configuration
 */
@Configuration
public class UserConfiguration {

    /**
     * User Information Transmission Filter
     */
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(
        StringRedisTemplate stringRedisTemplate) {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter(stringRedisTemplate));
        registration.addUrlPatterns("/*"); // Apply to all URL patterns
        registration.setOrder(0); // Set filter execution order
        return registration;
    }
}
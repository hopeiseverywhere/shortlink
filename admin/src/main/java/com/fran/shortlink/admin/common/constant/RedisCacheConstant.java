package com.fran.shortlink.admin.common.constant;

import org.springframework.stereotype.Component;

/**
 * Redis Cache
 */
@Component
public class RedisCacheConstant {
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";
}

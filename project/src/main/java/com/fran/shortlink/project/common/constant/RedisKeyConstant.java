package com.fran.shortlink.project.common.constant;

/**
 * Redis Key Constant
 */
public class RedisKeyConstant {

    /**
     * Short link redirecting prefix key
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * Short link redirecting lock prefix key
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";
}

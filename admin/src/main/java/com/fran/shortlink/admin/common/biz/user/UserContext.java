package com.fran.shortlink.admin.common.biz.user;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.Optional;

/**
 * User Context Utility Class
 */
public final class UserContext {

    // Use TransmittableThreadLocal to store user info and support thread transmission
    private static final ThreadLocal<UserInfoDTO> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * Set user information into the context
     *
     * @param user User information object
     */
    public static void setUser(UserInfoDTO user) {
        USER_THREAD_LOCAL.set(user);
    }

    /**
     * Get user ID from the context
     *
     * @return User ID
     */
    public static String getUserId() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUserId).orElse(null);
    }

    /**
     * Get username from the context
     *
     * @return Username
     */
    public static String getUsername() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUsername).orElse(null);
    }

    /**
     * Get real name from the context
     *
     * @return Real name
     */
    public static String getRealName() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getRealName).orElse(null);
    }

    /**
     * Remove user information from the context
     */
    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}

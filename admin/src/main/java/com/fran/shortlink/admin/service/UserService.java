package com.fran.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.admin.dao.entity.UserDO;
import com.fran.shortlink.admin.dto.req.UserLoginReqDTO;
import com.fran.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.fran.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.fran.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.fran.shortlink.admin.dto.resp.UserRespDTO;

/**
 * User Interface
 */
public interface UserService extends IService<UserDO> {

    /**
     * Get user info based on username
     *
     * @param username username to search
     * @return user response entity
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * Check if a username existed or not
     *
     * @param username username to search
     * @return true if existed, false otherwise
     */
    Boolean hasUsername(String username);

    /**
     * Register user
     *
     * @param requestParam user registration request params
     */
    void registerUser(UserRegisterReqDTO requestParam);

    /**
     * Update user based on username
     *
     * @param requestParam user update request params
     */
    void updateUser(UserUpdateReqDTO requestParam);

    /**
     * User login
     *
     * @param requestParam user login request params
     * @return user login response token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * Check if the user has logged in or not
     * @param username username
     * @param token user login token
     * @return true if logged in, false otherwise
     */
    Boolean checkLogin(String username, String token);

    /**
     * Log out user
     * @param username username
     * @param token user login token
     */
    void logout(String username, String token);
}

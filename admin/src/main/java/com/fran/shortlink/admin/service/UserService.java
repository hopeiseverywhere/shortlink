package com.fran.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.admin.dao.entity.UserDO;
import com.fran.shortlink.admin.dto.resp.UserRespDTO;

/**
 * User interface
 */
public interface UserService extends IService<UserDO> {

    /**
     * Get user info based on username
     * @param username username to search
     * @return user response entity
     */
    UserRespDTO getUserByUsername(String username);
}

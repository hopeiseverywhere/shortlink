package com.fran.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.admin.dao.entity.GroupDO;

/**
 * Short Link Group Interface
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * Create a new short link group name
     * @param groupName the new group name
     */
    void saveGroup(String groupName);
}

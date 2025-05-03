package com.fran.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.admin.dao.entity.GroupDO;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.fran.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import java.util.List;

/**
 * Short Link Group Interface
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * Create a new short link group name
     * @param groupName the new group name
     */
    void saveGroup(String groupName);

    /**
     * Search user short link group list
     * @return list of short link group
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * Update short link group
     * @param requestParam short link group param
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    /**
     * Delete short link group
     * @param gid short link group id
     */
    void deleteGroup(String gid);

    /**
     * Short link group sort order
     * @param requestParam short link group sort request param
     */
    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}

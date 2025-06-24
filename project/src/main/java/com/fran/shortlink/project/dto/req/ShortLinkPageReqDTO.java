package com.fran.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fran.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * Short link page request
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * Group identifier
     */
    private String gid;
}

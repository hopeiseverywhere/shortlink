package com.fran.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Short link page request
 */
@Data
public class ShortLinkPageReqDTO extends Page {

    /**
     * Group identifier
     */
    private String gid;
}

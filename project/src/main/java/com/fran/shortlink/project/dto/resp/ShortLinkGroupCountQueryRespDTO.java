package com.fran.shortlink.project.dto.resp;

import lombok.Data;

/**
 * Short Link Group Query Response DTO
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * Group identifier
     */
    private String gid;
    /**
     * Short link count
     */
    private Integer shortLinkCount;
}

package com.fran.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * Short Link Group Response DTO
 */
@Data
public class ShortLinkGroupRespDTO {

    /**
     * Group id
     */
    private String gid;

    /**
     * Group name
     */
    private String name;

    /**
     * Username of Group Creator
     */
    private String username;

    /**
     * Group Sort Order
     */
    private Integer sortOrder;
}

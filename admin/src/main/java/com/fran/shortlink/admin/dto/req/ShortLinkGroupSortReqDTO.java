package com.fran.shortlink.admin.dto.req;


import lombok.Data;

/**
 * Short Lint Group Sort Request DTO
 */
@Data
public class ShortLinkGroupSortReqDTO {

    /**
     * Group id
     */
    private String gid;

    /**
     * Sort Order
     */
    private Integer sortOrder;
}

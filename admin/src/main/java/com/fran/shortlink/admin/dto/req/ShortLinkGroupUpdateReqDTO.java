package com.fran.shortlink.admin.dto.req;


import lombok.Data;

/**
 * Short Lint Group Update Request DTO
 */
@Data
public class ShortLinkGroupUpdateReqDTO {

    /**
     * Group id
     */
    private String gid;
    /**
     * Group name
     */
    private String name;
}
